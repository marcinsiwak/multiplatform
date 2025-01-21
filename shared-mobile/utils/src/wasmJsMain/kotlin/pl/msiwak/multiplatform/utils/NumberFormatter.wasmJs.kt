package pl.msiwak.multiplatform.utils

actual class NumberFormatter actual constructor() {
    actual fun formatNumber(number: Double): String {
        return jsFormatNumber(number)
    }
}

external fun jsFormatNumber(number: Double): String
