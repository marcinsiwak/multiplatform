package pl.msiwak.multiplatform.utils

import platform.darwin.NSObject

class KMMPreferencesImpl(private val nsObject: NSObject) : KMMPreferences {

    override fun put(key: String, value: Int) {
        nsObject.putInt(key, value)
    }

    override fun put(key: String, value: String) {
        nsObject.putString(key, value)
    }

    override fun put(key: String, value: Boolean) {
        nsObject.putBool(key, value)
    }

    override fun getInt(key: String, default: Int): Int = nsObject.getInt(key, default)

    override fun getString(key: String): String? = nsObject.getString(key)

    override fun getBool(key: String, default: Boolean): Boolean =
        nsObject.getBool(key, default)
}
