package pl.msiwak.multiplatform.data.local.store

import pl.msiwak.multiplatform.utils.KMPPreferences

class LanguageStore(private val sharedKMPPreferences: KMPPreferences) {
    fun saveLanguage(languageCode: String) {
        sharedKMPPreferences.put(PREFS_LANGUAGE_KEY, languageCode)
    }

    fun geLanguage(): String {
        return sharedKMPPreferences.getString(PREFS_LANGUAGE_KEY) ?: DEFAULT_LANGUAGE_VALUE
    }

    companion object {
        const val PREFS_LANGUAGE_KEY = "PREFS_LANGUAGE_KEY"
        const val DEFAULT_LANGUAGE_VALUE = "en"
    }
}
