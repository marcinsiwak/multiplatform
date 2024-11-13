package pl.msiwak.infrastructure.config.auth.firebase

import io.ktor.server.auth.*

data class FirebaseUser(val userId: String = "", val displayName: String = "") : Principal
