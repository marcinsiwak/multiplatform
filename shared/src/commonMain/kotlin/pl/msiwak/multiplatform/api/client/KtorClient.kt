package pl.msiwak.multiplatform.api.client

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.BuildKonfig
import pl.msiwak.multiplatform.data.store.SessionStore
import pl.msiwak.multiplatform.utils.isDebug

class KtorClient(private val sessionStore: SessionStore) {
    val httpClient = HttpClient(CIO) {
        if (BuildKonfig.IsDebug) {
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
            json(Json {
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
                ignoreUnknownKeys = true

            })
        }
        defaultRequest {
            url(BuildKonfig.BASE_URL)
            bearerAuth(sessionStore.getToken() ?: "")

        }
    }
}