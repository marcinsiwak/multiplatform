package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.ExerciseRepository

class RemoveResultUseCaseImpl(private val exerciseRepository: ExerciseRepository) : RemoveResultUseCase {
    override suspend operator fun invoke(resultId: String) {
        val category = exerciseRepository.getCategoryByResult(resultId) ?: return
        category.removeResult(resultId)
        exerciseRepository.updateResults(category)
    }
}
