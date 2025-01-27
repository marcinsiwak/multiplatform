package pl.msiwak.infrastructure.config.auth.apikey

data class ApiKeyPrincipal(
    val apiKey: String,
    val nonce: String,
    val timestamp: String
)
