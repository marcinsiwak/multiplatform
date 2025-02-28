package pl.msiwak.multiplatform.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.buildconfig.BuildConfig
import pl.msiwak.multiplatform.commonObject.exception.ClientErrorException
import pl.msiwak.multiplatform.commonObject.exception.InvalidAuthTokenException
import pl.msiwak.multiplatform.commonObject.exception.ServerErrorException
import pl.msiwak.multiplatform.network.EngineProvider
import pl.msiwak.multiplatform.shared.common.CustomHttpHeaders
import pl.msiwak.multiplatform.shared.security.prepareDynamicApiKey
import pl.msiwak.multiplatform.store.SessionStore
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import co.touchlab.kermit.Logger as KermitLogger

class KtorClient(private val sessionStore: SessionStore, engine: EngineProvider) {
    val httpClient = HttpClient(engine.getEngine()) {

        if (BuildConfig.IsDebug) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        KermitLogger.i("HTTP Client: $message")
                    }
                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url(BuildConfig.BASE_URL)
            bearerAuth(sessionStore.getToken() ?: "")
            setupCorsHeaders()
            setupApiKeyHeaders()
            header(CustomHttpHeaders.PERMISSION_POLICY_HEADER, "fedcm=(self)")
        }

        HttpResponseValidator {
            validateResponse {
                if (!it.status.isSuccess()) {
                    KermitLogger.e("HTTP Client Error: ${it.status}")
                } else {
                    KermitLogger.v("HTTP Client: ${it.status}")
                }

                when (val statusCode = it.status.value) {
                    in CLIENT_ERROR_RANGE_START..CLIENT_ERROR_RANGE_END -> throw parseClientErrors(
                        statusCode,
                        it.status.description
                    )

                    in SERVER_ERROR_RANGE_START..SERVER_ERROR_RANGE_END -> throw parseServerErrors(
                        statusCode,
                        it.status.description
                    )
                }
            }
        }
    }

    private fun parseClientErrors(httpCode: Int, httpMessage: String): Throwable =
        when (httpCode) {
            HTTP_BAD_REQUEST -> parseBadRequest(httpCode, httpMessage)
            HTTP_UNPROCESSABLE_ENTITY -> ClientErrorException(httpCode, httpMessage)
            HTTP_FORBIDDEN -> parseForbidden(httpCode, httpMessage)
            HTTP_UNAUTHORIZED -> parseUnauthorized(httpCode, httpMessage)
            else -> ClientErrorException(httpCode, httpMessage)
        }

    private fun parseBadRequest(httpCode: Int, httpMessage: String?): Throwable =
        when (httpMessage) {
            // todo handle errors
            else -> ClientErrorException(httpCode, httpMessage)
        }

    private fun parseForbidden(httpCode: Int, httpMessage: String?): Throwable =
        when (httpMessage) {
            // todo handle errors
            else -> ClientErrorException(httpCode, httpMessage)
        }

    private fun parseUnauthorized(httpCode: Int, httpMessage: String?): Throwable =
        InvalidAuthTokenException(httpCode, httpMessage)

    private fun parseServerErrors(httpCode: Int, httpMessage: String?): Throwable =
        ServerErrorException(httpCode, httpMessage)

    @OptIn(ExperimentalUuidApi::class)
    private fun DefaultRequest.DefaultRequestBuilder.setupApiKeyHeaders() {
        val uuid = Uuid.random().toString()
        val timestamp = Clock.System.now().toEpochMilliseconds().toString()
        headers {
            append(
                CustomHttpHeaders.API_KEY_HEADER,
                prepareDynamicApiKey(apiKey = BuildConfig.API_KEY, nonce = uuid, timestamp = timestamp)
            )
            append(CustomHttpHeaders.API_KEY_NONCE_HEADER, uuid)
            append(CustomHttpHeaders.API_KEY_TIMESTAMP_HEADER, timestamp)
        }
    }

    private fun DefaultRequest.DefaultRequestBuilder.setupCorsHeaders() = headers {
        append(HttpHeaders.AccessControlAllowHeaders, "Content-Type")
        append(HttpHeaders.AccessControlAllowOrigin, "*")
        append(HttpHeaders.AccessControlAllowMethods, "OPTIONS,POST,GET")
    }

    companion object {
        const val CLIENT_ERROR_RANGE_START = 400
        const val CLIENT_ERROR_RANGE_END = 499
        const val SERVER_ERROR_RANGE_START = 500
        const val SERVER_ERROR_RANGE_END = 599
        const val HTTP_BAD_REQUEST = 400
        const val HTTP_UNPROCESSABLE_ENTITY = 422
        const val HTTP_FORBIDDEN = 403
        const val HTTP_UNAUTHORIZED = 401
    }
}
