package pl.msiwak.multiplatform.utils

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

fun NSObject.putInt(key: String, value: Int) {
    NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
}

fun NSObject.getInt(key: String, default: Int): Int {
    return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
}

fun NSObject.putString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}

fun NSObject.getString(key: String): String? {
    return NSUserDefaults.standardUserDefaults.stringForKey(key)
}

fun NSObject.putBool(key: String, value: Boolean) {
    NSUserDefaults.standardUserDefaults.setBool(value, key)
}

fun NSObject.getBool(key: String, default: Boolean): Boolean {
    return NSUserDefaults.standardUserDefaults.boolForKey(key)
}
