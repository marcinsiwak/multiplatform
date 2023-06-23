package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.store.SessionStore

class SessionRepository(private val sessionStore: SessionStore) {

    suspend fun saveToken(token: String) = withContext(Dispatchers.Default) {
        sessionStore.saveToken(token)
    }

    suspend fun getToken(): String? = withContext(Dispatchers.Default) {
        return@withContext sessionStore.getToken()
    }
}
