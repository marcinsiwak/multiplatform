package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

expect class FirebaseAuthorization {

    suspend fun createNewUser(email: String, password: String)

    suspend fun loginUser(email: String, password: String): AuthResult

    suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): AuthResult

    fun observeAuthStateChanged(): Flow<FirebaseUser?>

    suspend fun logoutUser()

    suspend fun resendVerificationEmail()
}
