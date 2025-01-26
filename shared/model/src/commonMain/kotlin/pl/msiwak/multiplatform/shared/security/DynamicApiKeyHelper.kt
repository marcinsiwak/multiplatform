package pl.msiwak.multiplatform.shared.security

fun prepareDynamicApiKey(backendApiKey: String, nonce: String, timestamp: String): String {
    return backendApiKey.plus(nonce).plus(timestamp).hash(HashAlgorithmSHA.SHA256).hash(HashAlgorithmSHA.SHA256)
}