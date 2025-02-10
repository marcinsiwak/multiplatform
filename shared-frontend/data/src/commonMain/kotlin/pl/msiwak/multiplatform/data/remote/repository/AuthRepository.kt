package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.auth.FirebaseAuthorization
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser
import pl.msiwak.multiplatform.commonObject.dispatcher.Dispatchers

class AuthRepository(
    private val firebaseAuthorization: FirebaseAuthorization
) {

    suspend fun createNewUser(email: String, password: String) = withContext(Dispatchers.IO) {
        return@withContext firebaseAuthorization.createNewUser(email, password)
    }

    suspend fun login(login: String, password: String): AuthResult = withContext(Dispatchers.IO) {
        return@withContext firebaseAuthorization.loginUser(login, password)
    }

    suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): AuthResult? =
        withContext(Dispatchers.IO) {
            return@withContext firebaseAuthorization.loginWithGoogle(googleToken, accessToken)
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
