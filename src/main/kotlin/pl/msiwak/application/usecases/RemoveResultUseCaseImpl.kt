package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository

class RemoveResultUseCaseImpl(private val exerciseRepository: ExerciseRepository) : RemoveResultUseCase {
    override suspend operator fun invoke(resultId: String) {
        val category = exerciseRepository.getCategoryByResult(resultId) ?: return
        category.removeResult(resultId)
        exerciseRepository.updateResults(category)
    }
}