package pl.msiwak.multiplatform.auth

import pl.msiwak.multiplatform.utils.KMMPreferences

class SessionStore(private val sharedPreferences: KMMPreferences) {

    fun saveToken(token: String) {
        sharedPreferences.put(PREFS_TOKEN_KEY, token)
    }

    fun clearToken() {
        sharedPreferences.put(PREFS_TOKEN_KEY, "")
    }

    fun getToken(): String? {
        return sharedPreferences.getString(PREFS_TOKEN_KEY)
    }

    companion object {
        const val PREFS_TOKEN_KEY = "PREFS_TOKEN_KEY"
    }
}