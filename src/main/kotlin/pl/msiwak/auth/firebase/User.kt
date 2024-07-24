package pl.msiwak.auth.firebase

import io.ktor.server.auth.*

data class User(val userId: String = "", val displayName: String = "") : Principal
