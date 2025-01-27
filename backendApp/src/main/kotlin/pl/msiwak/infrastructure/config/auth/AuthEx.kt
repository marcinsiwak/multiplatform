package pl.msiwak.infrastructure.config.auth

import io.ktor.server.auth.AuthenticationConfig
import pl.msiwak.infrastructure.config.auth.apikey.API_KEY_AUTH
import pl.msiwak.infrastructure.config.auth.apikey.ApiKeyAuthProvider
import pl.msiwak.infrastructure.config.auth.apikey.ApiKeyConfig

fun AuthenticationConfig.firebase(
    name: String? = pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH,
    configure: pl.msiwak.infrastructure.config.auth.firebase.FirebaseConfig.() -> Unit
) {
    val provider = pl.msiwak.infrastructure.config.auth.firebase.FirebaseAuthProvider(
        pl.msiwak.infrastructure.config.auth.firebase.FirebaseConfig(name).apply(configure)
    )
    register(provider)
}

fun AuthenticationConfig.apiKey(
    name: String? = API_KEY_AUTH,
    configure: ApiKeyConfig.() -> Unit
) {
    val provider = ApiKeyAuthProvider(ApiKeyConfig(name).apply(configure))
    register(provider)
}
