package pl.msiwak.entities

import kotlinx.serialization.Serializable
import pl.msiwak.util.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class CategoryEntity(
    val id: String? = null,
    val userId: String,
    val name: String,
    val exerciseType: String,
//    val exercises: List<ExerciseDTO>,
//    @Serializable(with = LocalDateTimeSerializer::class)
//    val creationDate: LocalDateTime
)
