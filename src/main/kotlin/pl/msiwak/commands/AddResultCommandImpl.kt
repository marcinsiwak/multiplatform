package pl.msiwak.commands

import pl.msiwak.entities.ResultEntity
import pl.msiwak.repositories.ExerciseRepository

class AddResultCommandImpl(private val exerciseRepository: ExerciseRepository) : AddResultCommand {
    override suspend fun invoke(exerciseId: String, resultEntity: ResultEntity, userId: String) {
        val category = exerciseRepository.getCategoryByExercise(exerciseId, userId) ?: return
        category.addResult(exerciseId, resultEntity)
        exerciseRepository.addResult(category)
    }
}