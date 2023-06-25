package pl.msiwak.multiplatform.repository

import dev.gitlive.firebase.auth.FirebaseUser
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization

class AuthRepository(
    private val firebaseAuthorization: FirebaseAuthorization
) {

    suspend fun login(login: String, password: String): String? = withContext(Dispatchers.Default) {
        val result = firebaseAuthorization.loginUser(login, password)
        Napier.e("OUTPUT: ${result.user?.getIdTokenResult(true)?.token}")
        return@withContext result.user?.getIdTokenResult(true)?.token
    }

    suspend fun observeAuthStateChanged(): Flow<FirebaseUser?> = withContext(Dispatchers.Default) {
        return@withContext firebaseAuthorization.observeAuthStateChanged()
    }

    companion object {
        const val PREFS_TOKEN_KEY = "PREFS_TOKEN_KEY"
    }
}