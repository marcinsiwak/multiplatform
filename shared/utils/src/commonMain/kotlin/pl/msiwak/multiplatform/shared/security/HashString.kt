package pl.msiwak.multiplatform.shared.security

import okio.ByteString.Companion.encodeUtf8

fun String.hash(algorithm: HashAlgorithmSHA = HashAlgorithmSHA.SHA1): String = when (algorithm) {
    HashAlgorithmSHA.SHA1 -> encodeUtf8().sha1().hex()
    HashAlgorithmSHA.SHA256 -> encodeUtf8().sha256().hex()
}

enum class HashAlgorithmSHA {
    SHA1, SHA256
}
