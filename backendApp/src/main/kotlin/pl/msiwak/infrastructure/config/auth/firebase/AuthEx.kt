package pl.msiwak.infrastructure.config.auth.firebase

import io.ktor.server.auth.AuthenticationConfig

fun AuthenticationConfig.firebase(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(name).apply(configure))
    register(provider)
}
