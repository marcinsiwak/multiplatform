package pl.msiwak.infrastructure.config.auth

import io.ktor.server.auth.AuthenticationConfig
import pl.msiwak.infrastructure.config.auth.apikey.API_KEY_AUTH
import pl.msiwak.infrastructure.config.auth.apikey.ApiKeyAuthProvider
import pl.msiwak.infrastructure.config.auth.apikey.ApiKeyConfig
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseAuthProvider
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseConfig

fun AuthenticationConfig.firebase(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(name).apply(configure))
    register(provider)
}

fun AuthenticationConfig.apiKey(
    name: String? = API_KEY_AUTH,
    configure: ApiKeyConfig.() -> Unit
) {
    val provider = ApiKeyAuthProvider(ApiKeyConfig(name).apply(configure))
    register(provider)
}
