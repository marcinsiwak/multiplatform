package pl.msiwak.multiplatform.network

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.buildJsonObject

class FirebaseApi {

    private val firebaseClient: FirebaseClient = FirebaseClient()

    suspend fun loginUserWithGoogle(idToken: String) {
        firebaseClient.httpClient.post("https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key=AIzaSyABbsF1ktwz4i5CNrXYs-kcU2CzqW7UWXs") {
            setBody(buildJsonObject {
//                put("postBody", "id_token=$googleIdToken&providerId=google.com")
//                put("requestUri", requestUri)
//                put("returnIdpCredential", true)
//                put("returnSecureToken", true)
            })
        }

    }
}