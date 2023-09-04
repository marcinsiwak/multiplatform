package pl.msiwak.multiplatform.core.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.core.data.common.Exercise

val exerciseListAdapter = object : ColumnAdapter<List<Exercise>, String> {
    override fun decode(databaseValue: String): List<Exercise> {
        return if (databaseValue.isEmpty()) {
            listOf()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: List<Exercise>): String {
        return Json.encodeToString(value)
    }
}