package pl.msiwak.multiplatform.core.utils

import kotlinx.datetime.LocalDateTime

expect class DateFormatter() {
    fun formatDate(
        date: LocalDateTime,
        format: String = DATE_FORMAT
    ): String

    fun formatString(
        date: String,
        format: String
    ): LocalDateTime
}