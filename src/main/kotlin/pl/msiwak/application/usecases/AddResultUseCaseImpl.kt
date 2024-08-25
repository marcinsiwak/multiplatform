package pl.msiwak.application.usecases

import kotlinx.datetime.LocalDateTime
import pl.msiwak.domain.entities.ResultEntity
import pl.msiwak.domain.repositories.ExerciseRepository

class AddResultUseCaseImpl(private val exerciseRepository: ExerciseRepository) : AddResultUseCase {
    override suspend operator fun invoke(
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