package pl.msiwak.multiplatform.utils

external fun setCookie(name: String, value: String, days: Int)
external fun setCookie(name: String, value: Boolean, days: Int)
external fun setCookie(name: String, value: Int, days: Int)
external fun getCookie(name: String): String?
external fun clearCookie(name: String)
