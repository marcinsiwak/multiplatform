package pl.msiwak.infrastructure.config.auth.defaultAuth

data class ApiKeyPrincipal(
    val apiKey: String,
    val nonce: String,
    val timestamp: String
)
