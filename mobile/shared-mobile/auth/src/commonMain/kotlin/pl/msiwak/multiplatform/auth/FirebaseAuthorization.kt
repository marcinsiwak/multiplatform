package pl.msiwak.multiplatform.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow

class FirebaseAuthorization {

    private val auth = Firebase.auth

    suspend fun createNewUser(email: String, password: String) {
        val result = auth.createUserWithEmailAndPassword(email, password)
        result.user?.sendEmailVerification()
    }

    suspend fun loginUser(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password)
    }

    suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): AuthResult {
        return auth.signInWithCredential(
            authCredential = GoogleAuthProvider.credential(
                idToken = googleToken,
                accessToken = accessToken
            )
        )
    }

    fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        return auth.authStateChanged
    }

    suspend fun logoutUser() {
        auth.signOut()
    }

    suspend fun resendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()
    }
}
