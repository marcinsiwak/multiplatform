package pl.msiwak.multiplatform.utils

private const val STORE_FOREVER = 1000000

class KMMPreferencesImpl : KMMPreferences {

    override fun put(key: String, value: Int) {
        setCookie(key, value, STORE_FOREVER)
    }

    override fun put(key: String, value: String) {
        if (value.isEmpty()) {
            clearCookie(key)
        } else {
            setCookie(key, value, STORE_FOREVER)
        }
    }

    override fun put(key: String, value: Boolean) {
        setCookie(key, value, STORE_FOREVER)
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
