package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.AuthProvider
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

interface FirebaseAuthorization {

    suspend fun createNewUser(email: String, password: String): AuthResult?

    suspend fun loginUser(email: String, password: String): AuthResult

    suspend fun loginWithProvider(authProvider: AuthProvider): AuthResult

    fun observeAuthStateChanged(): Flow<FirebaseUser?>

    suspend fun logoutUser()

    suspend fun resendVerificationEmail()
}
