package pl.msiwak.multiplatform.utils

import android.content.Context

class KMMPreferencesImpl(private val context: Context) : KMMPreferences {

    override fun put(key: String, value: Int, isHttpOnly: Boolean) {
        context.putInt(key, value)
    }

    override fun put(key: String, value: String, isHttpOnly: Boolean) {
        context.putString(key, value)
    }

    override fun put(key: String, value: Boolean, isHttpOnly: Boolean) {
        context.putBool(key, value)
    }

    override fun getInt(key: String, default: Int): Int = context.getInt(key, default)

    override fun getString(key: String): String? = context.getString(key)

    override fun getBool(key: String, default: Boolean): Boolean =
        context.getBool(key, default)
}
