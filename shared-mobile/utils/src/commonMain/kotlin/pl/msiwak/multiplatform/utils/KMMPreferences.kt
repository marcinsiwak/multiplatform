package pl.msiwak.multiplatform.utils

interface KMMPreferences {

    fun put(key: String, value: Int, isHttpOnly: Boolean = false)

    fun put(key: String, value: String, isHttpOnly: Boolean = false)

    fun put(key: String, value: Boolean, isHttpOnly: Boolean = false)

    fun getInt(key: String, default: Int): Int

    fun getString(key: String): String?

    fun getBool(key: String, default: Boolean): Boolean
}
