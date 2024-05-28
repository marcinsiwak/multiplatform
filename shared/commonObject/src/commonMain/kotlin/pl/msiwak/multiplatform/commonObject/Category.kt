package pl.msiwak.multiplatform.commonObject

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Category(
    val id: String = "",
    val name: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    val exercises: List<Exercise> = emptyList(),
    val creationDate: LocalDateTime = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
)
