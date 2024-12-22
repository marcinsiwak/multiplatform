package pl.msiwak.multiplatform.utils

private const val STORE_FOREVER = 1000000

class KMMPreferencesImpl : KMMPreferences {

    override fun put(key: String, value: Int, isHttpOnly: Boolean) {
        setCookie(key, value, STORE_FOREVER, isHttpOnly = isHttpOnly)
    }

    override fun put(key: String, value: String, isHttpOnly: Boolean) {
        setCookie(key, value, STORE_FOREVER, isHttpOnly = isHttpOnly)
    }

    override fun put(key: String, value: Boolean, isHttpOnly: Boolean) {
        setCookie(key, value, STORE_FOREVER, isHttpOnly = isHttpOnly)
    }

    override fun getInt(key: String, default: Int): Int {
        return getCookie(key)?.toInt() ?: default
    }

    override fun getString(key: String): String? {
        return getCookie(key)
    }

    override fun getBool(key: String, default: Boolean): Boolean {
        return getCookie(key)?.toBoolean() ?: default
    }
}
