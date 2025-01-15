package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import platform.Foundation.NSDate

fun toKotlinDateTime(date: NSDate): LocalDateTime {
    return date.toKotlinInstant().toLocalDateTime(TimeZone.currentSystemDefault())
}
