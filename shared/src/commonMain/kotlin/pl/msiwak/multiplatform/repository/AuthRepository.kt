package pl.msiwak.multiplatform.repository

import pl.msiwak.multiplatform.utils.KMMPreferences

class AuthRepository(private val sharedKMMPreferences: KMMPreferences) {
    fun saveToken(token: String) {
        sharedKMMPreferences.put(PREFS_TOKEN_KEY, token)
    }

    fun getToken(): String? {
        return sharedKMMPreferences.getString(PREFS_TOKEN_KEY)
    }

    companion object {
        const val PREFS_TOKEN_KEY = "PREFS_TOKEN_KEY"
    }
}