package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant

class ApiCategory(
    val categoryId: String = "",
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList(),
    val creationDate: Instant
)
