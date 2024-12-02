package pl.msiwak.multiplatform.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

actual class DateFormatter actual constructor() {
    actual fun formatDate(date: LocalDateTime, format: String): String {
        return "" // todo
    }

    actual fun formatString(date: String, format: String): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.UTC) // todo
    }

}