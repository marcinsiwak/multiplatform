package pl.msiwak.commands

import kotlinx.datetime.LocalDateTime
import pl.msiwak.entities.ResultEntity
import pl.msiwak.repositories.ExerciseRepository

class AddResultCommandImpl(private val exerciseRepository: ExerciseRepository) : AddResultCommand {
    override suspend fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    ) {
        val category = exerciseRepository.getCategoryByExercise(exerciseId) ?: return
        val resultEntity = ResultEntity(exerciseId, amount, result, date)
        category.addResult(exerciseId, resultEntity)
        exerciseRepository.updateResults(category)
    }
}