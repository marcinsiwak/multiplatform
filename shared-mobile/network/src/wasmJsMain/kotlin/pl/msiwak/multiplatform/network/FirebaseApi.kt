package pl.msiwak.multiplatform.network

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import pl.msiwak.multiplatform.buildconfig.BuildConfig

// todo remove firebaseApi when gitlive will support wasm

class FirebaseApi(private val firebaseClient: FirebaseClient) {

    suspend fun loginUserWithGoogle(idToken: String): AuthResponse {
        val response =
            firebaseClient.httpClient.post("https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key=${BuildConfig.firebaseKey}") {
                setBody(
                    buildJsonObject {
                        put("postBody", "id_token=$idToken&providerId=google.com")
                        put("returnIdpCredential", true)
                        put("returnSecureToken", true)
                        put("requestUri", "http://localhost:3000")
                    }
                )
            }.bodyAsText()
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }

    suspend fun loginUserWithPassword(email: String, password: String): AuthResponse {
        val response =
            firebaseClient.httpClient.post("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${BuildConfig.firebaseKey}") {
                setBody(
                    buildJsonObject {
                        put("email", email)
                        put("password", password)
                        put("returnSecureToken", true)
                    }
                )
            }.bodyAsText()
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString(response)
    }
}
