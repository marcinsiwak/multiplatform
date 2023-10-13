package pl.msiwak.multiplatform.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val localDateTimeAdapter = object : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime {
        return if (databaseValue.isEmpty()) {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: LocalDateTime): String {
        return Json.encodeToString(value)
    }
}