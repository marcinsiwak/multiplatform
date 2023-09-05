package pl.msiwak.multiplatform.database

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.commonObject.ExerciseType

val exerciseTypeAdapter = object : ColumnAdapter<ExerciseType, String> {
    override fun decode(databaseValue: String): ExerciseType {
        return if (databaseValue.isEmpty()) {
            ExerciseType.GYM
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: ExerciseType): String {
        return Json.encodeToString(value)
    }
}