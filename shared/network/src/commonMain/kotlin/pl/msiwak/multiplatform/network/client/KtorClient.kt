package pl.msiwak.multiplatform.network.client

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.auth.SessionStore
import pl.msiwak.multiplatform.buildconfig.BuildConfig

class KtorClient(private val sessionStore: SessionStore) {
    val httpClient = HttpClient(CIO) {
        if (BuildConfig.IsDebug) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v("HTTP Client", null, message)
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
        }

        HttpResponseValidator {
            validateResponse {
                if (!it.status.isSuccess()) {
                    Napier.e("HTTP Client Error: ${it.status}")
                } else {
                    Napier.v("HTTP Client: ${it.status}")
                }
            }
        }
    }
}
