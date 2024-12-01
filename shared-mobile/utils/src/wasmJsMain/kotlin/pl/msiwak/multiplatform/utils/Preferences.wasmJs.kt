package pl.msiwak.multiplatform.utils

actual fun KMMContext.putInt(key: String, value: Int) {
}

actual fun KMMContext.getInt(key: String, default: Int): Int {
    return 0
}

actual fun KMMContext.putString(key: String, value: String) {

}

actual fun KMMContext.getString(key: String): String? {
    return ""
}

actual fun KMMContext.putBool(key: String, value: Boolean) {

}

actual fun KMMContext.getBool(key: String, default: Boolean): Boolean {
 return true
}