package pl.msiwak.infrastructure.entities

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CategoryEntity(
    val id: String? = null,
    val userId: String,
    val name: String,
    val exerciseType: String,
    val exercises: HashSet<ExerciseEntity> = hashSetOf()
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

    fun getExercise(exerciseId: String): ExerciseEntity? {
        return exercises.firstOrNull { exercise -> exercise.id == exerciseId }
    }

    fun removeExercise(exerciseId: String) {
        exercises.removeIf { it.id == exerciseId }
    }

    fun addResult(exerciseId: String, resultEntity: ResultEntity) {
        exercises.find { it.id == exerciseId }?.addResult(resultEntity)
    }

    fun removeResult(resultId: String) {
        exercises.find { it.results.any { it.id == resultId } }?.removeResult(resultId)
    }
}
