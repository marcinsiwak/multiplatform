package pl.msiwak.multiplatform.commonObject

sealed class AuthProvider {
    class Google(val tokenId: String?, val accessToken: String?) : AuthProvider()
    class Apple(val idToken: String, val nonce: String) : AuthProvider()
}
