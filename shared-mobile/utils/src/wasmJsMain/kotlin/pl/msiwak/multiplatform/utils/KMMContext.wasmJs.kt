package pl.msiwak.multiplatform.utils

external fun setCookie(name: String, value: String, days: Int, isHttpOnly: Boolean)
external fun setCookie(name: String, value: Boolean, days: Int, isHttpOnly: Boolean)
external fun setCookie(name: String, value: Int, days: Int, isHttpOnly: Boolean)
external fun getCookie(name: String): String?
