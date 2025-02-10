package pl.msiwak.multiplatform.store

import pl.msiwak.multiplatform.utils.KMPPreferences

class SessionStore(private val sharedPreferences: KMPPreferences) {

    fun saveToken(token: String) {
        sharedPreferences.put(PREFS_TOKEN_KEY, token)
    }

    fun clearToken() {
        sharedPreferences.put(PREFS_TOKEN_KEY, "")
    }

    fun getToken(): String? {
        return sharedPreferences.getString(PREFS_TOKEN_KEY)
    }

    fun setOfflineSession(isOfflineSession: Boolean) {
        sharedPreferences.put(PREFS_OFFLINE_KEY, isOfflineSession)
    }

    fun getIsOfflineSession(): Boolean {
        return sharedPreferences.getBool(PREFS_OFFLINE_KEY, false)
    }

    fun saveMessagingToken(token: String) {
        sharedPreferences.put(PREFS_MESSAGING_TOKEN_KEY, token)
    }

    fun clearMessagingToken() {
        sharedPreferences.put(PREFS_MESSAGING_TOKEN_KEY, "")
    }

    fun getMessagingToken() = sharedPreferences.getString(PREFS_MESSAGING_TOKEN_KEY)

    companion object {
        const val PREFS_TOKEN_KEY = "PREFS_TOKEN_KEY"
        const val PREFS_OFFLINE_KEY = "PREFS_OFFLINE_KEY"
        const val PREFS_MESSAGING_TOKEN_KEY = "PREFS_MESSAGING_TOKEN_KEY"
    }
}
