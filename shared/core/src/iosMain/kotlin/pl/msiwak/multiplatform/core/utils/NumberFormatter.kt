package pl.msiwak.multiplatform.core.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual class NumberFormatter actual constructor() {
    actual fun formatNumber(number: Double): String {
        val formatter = NSNumberFormatter()
        formatter.minimumFractionDigits = 0u
        formatter.maximumFractionDigits = 2u
        formatter.numberStyle = 1u //Decimal
        return formatter.stringFromNumber(NSNumber(number))!!
    }
}