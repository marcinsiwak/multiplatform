package pl.msiwak.entities

import kotlinx.serialization.Serializable
import java.util.*
import kotlin.collections.HashSet


@Serializable
data class CategoryEntity(
    val id: String? = null,
    val userId: String,
    val name: String,
    val exerciseType: String,
    val exercises: HashSet<ExerciseEntity> = hashSetOf(),
//    @Serializable(with = LocalDateTimeSerializer::class)
//    val creationDate: LocalDateTime
) {

    constructor(userId: String, name: String, exerciseType: String) : this(
        id = UUID.randomUUID().toString(),
        userId = userId,
        name = name,
        exerciseType = exerciseType
    )

    fun addExercise(exerciseEntity: ExerciseEntity) {
        exercises.add(exerciseEntity)
    }

    fun addResult(exerciseId: String, resultEntity: ResultEntity) {
        exercises.find { it.id == exerciseId }?.addResult(resultEntity)
    }

    fun getExercise(exerciseId: String): ExerciseEntity? {
        return exercises.firstOrNull { exercise -> exercise.id == exerciseId }
    }

}
