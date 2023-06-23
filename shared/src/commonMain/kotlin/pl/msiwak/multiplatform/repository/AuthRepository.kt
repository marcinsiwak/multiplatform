package pl.msiwak.multiplatform.repository

import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
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

    companion object {
        const val PREFS_TOKEN_KEY = "PREFS_TOKEN_KEY"
    }
}