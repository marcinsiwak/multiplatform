package pl.msiwak.multiplatform.data.remote.repository

import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.auth.FirebaseAuthorization

class AuthRepository(
    private val firebaseAuthorization: FirebaseAuthorization
) {

    suspend fun login(login: String, password: String): AuthResult? = withContext(Dispatchers.IO) {
        return@withContext firebaseAuthorization.loginUser(login, password)
    }

    suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): String? =
        withContext(Dispatchers.IO) {
            val result = firebaseAuthorization.loginWithGoogle(googleToken, accessToken)
            return@withContext result.user?.getIdTokenResult(true)?.token
        }

    suspend fun observeAuthStateChanged(): Flow<FirebaseUser?> = withContext(Dispatchers.IO) {
        return@withContext firebaseAuthorization.observeAuthStateChanged()
    }

    suspend fun logoutUser() = withContext(Dispatchers.IO) {
        firebaseAuthorization.logoutUser()
    }

    suspend fun resendVerificationEmail() = withContext(Dispatchers.IO) {
        firebaseAuthorization.resendVerificationEmail()
    }
}