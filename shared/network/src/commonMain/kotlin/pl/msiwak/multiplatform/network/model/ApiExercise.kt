package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import pl.msiwak.multiplatform.commonObject.ExerciseType

@Serializable
class ApiExercise(
    val exerciseId: String,
    val categoryId: String,
    val name: String,
    val exerciseType: String,
    val results: List<ApiResult> = emptyList(),
    val creationDate: Instant
)