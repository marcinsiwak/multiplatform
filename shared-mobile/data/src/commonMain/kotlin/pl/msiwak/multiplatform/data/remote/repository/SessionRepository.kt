package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.store.SessionStore

class SessionRepository(private val sessionStore: SessionStore) {

    suspend fun saveToken(token: String) = withContext(Dispatchers.IO) {
        sessionStore.saveToken(token)
    }

    suspend fun clearToken() = withContext(Dispatchers.IO) {
        sessionStore.clearToken()
    }

    suspend fun getToken(): String? = withContext(Dispatchers.IO) {
        return@withContext sessionStore.getToken()
    }

    suspend fun setOfflineSession(isOfflineSession: Boolean) = withContext(Dispatchers.IO) {
        sessionStore.setOfflineSession(isOfflineSession)
    }

    suspend fun getIsOfflineSession() = withContext(Dispatchers.IO) {
        sessionStore.getIsOfflineSession()
    }
}
