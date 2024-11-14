package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository

class RemoveCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : RemoveCategoryUseCase {

    override suspend operator fun invoke(categoryId: String) {
        exerciseRepository.removeCategory(categoryId)
    }
}
