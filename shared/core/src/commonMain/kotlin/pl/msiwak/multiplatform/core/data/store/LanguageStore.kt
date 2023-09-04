package pl.msiwak.multiplatform.core.data.store

import pl.msiwak.multiplatform.core.utils.KMMPreferences

class LanguageStore(private val sharedKMMPreferences: KMMPreferences) {
    fun saveLanguage(languageCode: String) {
        sharedKMMPreferences.put(PREFS_LANGUAGE_KEY, languageCode)
    }

    fun geLanguage(): String {
        return sharedKMMPreferences.getString(PREFS_LANGUAGE_KEY) ?: DEFAULT_LANGUAGE_VALUE
    }

    companion object {
        const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
        const val DEFAULT_LANGUAGE_VALUE = "en"
    }
}