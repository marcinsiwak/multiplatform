package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.service.UserService
import pl.msiwak.multiplatform.store.SessionStore

class SessionRepository(
    private val sessionStore: SessionStore,
    private val userService: UserService
) {

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

    suspend fun clearMessagingToken() = withContext(Dispatchers.IO) {
        sessionStore.clearMessagingToken()
    }

    suspend fun registerDeviceForNotifications() = withContext(Dispatchers.IO) {
        val token = sessionStore.getMessagingToken()
        if (!token.isNullOrEmpty()) {
            userService.registerDeviceForNotifications(token)
        }
    }
}
