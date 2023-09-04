package pl.msiwak.multiplatform.core.utils


const val APPLICATION_SHARED_PREFS = "application_shared_prefs"

actual fun KMMContext.putInt(key: String, value: Int) {
    getSharedPreferencesEditor().putInt(key, value).apply()
}

actual fun KMMContext.getInt(key: String, default: Int): Int {
    return getSharedPreferences().getInt(key, default)
}

actual fun KMMContext.putString(key: String, value: String) {
    getSharedPreferencesEditor().putString(key, value).apply()
}

actual fun KMMContext.getString(key: String): String? {
    return getSharedPreferences().getString(key, null)
}

actual fun KMMContext.putBool(key: String, value: Boolean) {
    getSharedPreferencesEditor().putBoolean(key, value).apply()
}

actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
    return getSharedPreferences().getBoolean(key, default)
}

private fun KMMContext.getSharedPreferences() = getSharedPreferences(
    APPLICATION_SHARED_PREFS, android.content.Context.MODE_PRIVATE
)

private fun KMMContext.getSharedPreferencesEditor() = getSharedPreferences().edit()