package pl.msiwak.multiplatform.database

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import pl.msiwak.multiplatform.data.common.ExerciseType

val exerciseTypeAdapter = object : ColumnAdapter<ExerciseType, String> {
    override fun decode(databaseValue: String): ExerciseType {
        return if (databaseValue.isEmpty()) {
            ExerciseType.Gym()
        } else {
            Json.decodeFromString(databaseValue)
        }
    }

    override fun encode(value: ExerciseType): String {
        TODO("Not yet implemented")
    }
}