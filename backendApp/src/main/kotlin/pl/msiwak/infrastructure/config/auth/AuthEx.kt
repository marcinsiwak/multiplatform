package pl.msiwak.infrastructure.config.auth

import io.ktor.server.auth.AuthenticationConfig
import pl.msiwak.infrastructure.config.auth.defaultAuth.DEFAULT_AUTH
import pl.msiwak.infrastructure.config.auth.defaultAuth.DefaultAuthConfig
import pl.msiwak.infrastructure.config.auth.defaultAuth.DefaultAuthProvider
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseAuthProvider
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseConfig

fun AuthenticationConfig.loggedUserAuth(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseConfig.() -> Unit
) {
    val provider = FirebaseAuthProvider(FirebaseConfig(name).apply(configure))
    register(provider)
}

fun AuthenticationConfig.defaultAuth(
    name: String? = DEFAULT_AUTH,
    configure: DefaultAuthConfig.() -> Unit
) {
    val provider = DefaultAuthProvider(DefaultAuthConfig(name).apply(configure))
    register(provider)
}
