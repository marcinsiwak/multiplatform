package pl.msiwak.multiplatform.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val instantAdapter = object : ColumnAdapter<Instant, String> {
    override fun decode(databaseValue: String): Instant {
        return if (databaseValue.isEmpty()) {
            Clock.System.now()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: Instant): String {
        return Json.encodeToString(value)
    }
}