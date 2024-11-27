package pl.msiwak.multiplatform.utils

actual class NumberFormatter actual constructor() {
    actual fun formatNumber(number: Double): String {
        val df = java.text.DecimalFormat()
        df.isGroupingUsed = false
        df.maximumFractionDigits = 2
        df.isDecimalSeparatorAlwaysShown = false
        return df.format(number)
    }
}
