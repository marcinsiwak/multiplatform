package pl.msiwak.multiplatform.api.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.data.store.SessionStore

class KtorClient(private val sessionStore: SessionStore) {
    val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                encodeDefaults = true
                ignoreUnknownKeys = true
            })
//            defaultRequest {
//                header("Authorization", "Bearer ${sessionStore.getToken()}")
//            }
        }
    }
}