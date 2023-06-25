package pl.msiwak.multiplatform.data.store

import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.utils.KMMPreferences

class SessionStore(private val sharedPreferences: KMMPreferences) {

    fun saveToken(token: String) {
        sharedPreferences.put(AuthRepository.PREFS_TOKEN_KEY, token)
    }

    fun clearToken() {
        sharedPreferences.put(AuthRepository.PREFS_TOKEN_KEY, "")
    }

    fun getToken(): String? {
        return sharedPreferences.getString(AuthRepository.PREFS_TOKEN_KEY)
    }
}