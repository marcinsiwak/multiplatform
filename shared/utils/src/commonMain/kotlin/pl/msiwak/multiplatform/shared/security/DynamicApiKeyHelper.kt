package pl.msiwak.multiplatform.shared.security

fun prepareDynamicApiKey(apiKey: String, nonce: String, timestamp: String): String {
    return apiKey.plus(nonce).plus(timestamp).hash(HashAlgorithmSHA.SHA256).hash(HashAlgorithmSHA.SHA256)
}
