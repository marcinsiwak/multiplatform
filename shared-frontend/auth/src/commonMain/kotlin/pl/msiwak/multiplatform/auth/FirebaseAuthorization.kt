package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

interface FirebaseAuthorization {

    suspend fun createNewUser(email: String, password: String): AuthResult?

    suspend fun loginUser(email: String, password: String): AuthResult

    suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): AuthResult

    fun observeAuthStateChanged(): Flow<FirebaseUser?>

    suspend fun logoutUser()

    suspend fun resendVerificationEmail()
}
