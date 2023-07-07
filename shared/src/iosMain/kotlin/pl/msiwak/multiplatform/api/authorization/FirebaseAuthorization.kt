package pl.msiwak.multiplatform.api.authorization

//import dev.gitlive.firebase.Firebase
//import dev.gitlive.firebase.auth.AuthResult
//import dev.gitlive.firebase.auth.FirebaseUser
//import dev.gitlive.firebase.auth.GoogleAuthProvider
//import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow

import cocoapods.FirebaseAuth.*


actual class FirebaseAuthorization {

    private val auth = FIRAuth.auth()

    actual suspend fun createNewUser(email: String, password: String) {
        auth.createUserWithEmail(email, password, null)
//        auth.createUserWithEmailAndPassword(email, password)
    }

    actual suspend fun loginUser(email: String, password: String): String? {
        return auth.(email, password)
    }

    actual suspend fun loginWithGoogle(googleToken: String): String? {
        TODO("Not yet implemented")
    }
//
//    suspend fun createNewUser(email: String, password: String) {
//
//        auth.createUserWithEmailAndPassword(email, password)
//    }
//
//    suspend fun loginUser(email: String, password: String): AuthResult {
//        return auth.signInWithEmailAndPassword(email, password)
//    }
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