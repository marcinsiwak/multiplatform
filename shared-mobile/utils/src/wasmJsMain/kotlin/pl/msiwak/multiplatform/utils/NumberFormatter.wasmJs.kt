package pl.msiwak.multiplatform.utils

actual class NumberFormatter actual constructor() {
    actual fun formatNumber(number: Double): String {
        return JsNumberFormatter.formatNumber(number)
    }
}

@JsModule("./NumberFormatter.js")
external object JsNumberFormatter {
    fun formatNumber(number: Double): String
}
