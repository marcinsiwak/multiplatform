package pl.msiwak.multiplatform.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.data.common.ExerciseShort

val exerciseListAdapter = object : ColumnAdapter<List<ExerciseShort>, String> {
    override fun decode(databaseValue: String): List<ExerciseShort> {
        return if (databaseValue.isEmpty()) {
            listOf()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: List<ExerciseShort>): String {
        return Json.encodeToString(value)
    }
}