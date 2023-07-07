package pl.msiwak.multiplatform.api.authorization

import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


actual class FirebaseAuthorization {

    private val auth = Firebase.auth

    actual suspend fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    actual suspend fun loginUser(email: String, password: String): String? {
        val authResult = auth.signInWithEmailAndPassword(email, password)
        return authResult.result.user?.getIdToken(true)?.result?.token
    }

    actual suspend fun loginWithGoogle(googleToken: String): String? {
        val authResult = auth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                /* idToken = */ googleToken,
                /* accessToken = */ null
            )
        )
        return authResult.result.user?.getIdToken(true)?.result?.token
    }

//
//    suspend fun loginWithGoogle(googleToken: String): AuthResult {
//        return auth.signInWithCredential(
//            authCredential = GoogleAuthProvider.credential(
//                idToken = googleToken,
//                accessToken = null
//            )
//        )
//    }
//
//    fun observeAuthStateChanged(): Flow<FirebaseUser?> {
//        return auth.authStateChanged
//    }
//
//    suspend fun logoutUser() {
//        auth.signOut()
//    }

}
