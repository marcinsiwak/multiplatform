package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.auth.SessionStore

class SessionRepository(private val sessionStore: SessionStore) {

    suspend fun saveToken(token: String) = withContext(Dispatchers.Default) {
        sessionStore.saveToken(token)
    }

    suspend fun clearToken() = withContext(Dispatchers.Default) {
        sessionStore.clearToken()
    }

    suspend fun getToken(): String? = withContext(Dispatchers.Default) {
        return@withContext sessionStore.getToken()
    }
}
