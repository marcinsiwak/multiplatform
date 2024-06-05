package pl.msiwak.multiplatform.commonObject.extenstion

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Any> T.serializeToJson(): String {
    return Json.encodeToString<T>(this)
}

inline fun <reified T : Any> String.deserializeToObject(): T {
    return Json.decodeFromString(this)
}
