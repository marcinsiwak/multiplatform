package pl.msiwak.multiplatform.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

actual class FirebaseAuthorization {

    private val auth = Firebase.auth

    actual suspend fun createNewUser(email: String, password: String) {
        val result = auth.createUserWithEmailAndPassword(email, password)
        result.user?.sendEmailVerification()
    }

    actual suspend fun loginUser(email: String, password: String): pl.msiwak.multiplatform.commonObject.AuthResult {
        return auth.signInWithEmailAndPassword(email, password).let {
            pl.msiwak.multiplatform.commonObject.AuthResult(
                token = it.user?.getIdTokenResult(true)?.token
            )
        }
    }

    actual suspend fun loginWithGoogle(
        googleToken: String?,
        accessToken: String?
    ): pl.msiwak.multiplatform.commonObject.AuthResult {
        return auth.signInWithCredential(
            authCredential = GoogleAuthProvider.credential(
                idToken = googleToken,
                accessToken = accessToken
            )
        ).let {
            pl.msiwak.multiplatform.commonObject.AuthResult(
                token = it.user?.getIdTokenResult(true)?.token
            )
        }
    }

    actual fun observeAuthStateChanged(): Flow<pl.msiwak.multiplatform.commonObject.FirebaseUser?> {
        return auth.authStateChanged.map {
            pl.msiwak.multiplatform.commonObject.FirebaseUser(
                it?.uid,
                it?.email,
                it?.displayName,
                it?.isEmailVerified
            )
        }
    }

    actual suspend fun logoutUser() {
        auth.signOut()
    }

    actual suspend fun resendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()
    }
}
