package pl.msiwak.multiplatform.utils

import android.content.Context

private const val APPLICATION_SHARED_PREFS = "application_shared_prefs"

fun Context.putInt(key: String, value: Int) {
    getSharedPreferencesEditor().putInt(key, value).apply()
}

fun Context.getInt(key: String, default: Int): Int {
    return getSharedPreferences().getInt(key, default)
}

fun Context.putString(key: String, value: String) {
    getSharedPreferencesEditor().putString(key, value).apply()
}

fun Context.getString(key: String): String? {
    return getSharedPreferences().getString(key, null)
}

fun Context.putBool(key: String, value: Boolean) {
    getSharedPreferencesEditor().putBoolean(key, value).apply()
}

fun Context.getBool(key: String, default: Boolean): Boolean {
    return getSharedPreferences().getBoolean(key, default)
}

private fun Context.getSharedPreferences() = getSharedPreferences(
    APPLICATION_SHARED_PREFS,
    android.content.Context.MODE_PRIVATE
)

private fun Context.getSharedPreferencesEditor() = getSharedPreferences().edit()
