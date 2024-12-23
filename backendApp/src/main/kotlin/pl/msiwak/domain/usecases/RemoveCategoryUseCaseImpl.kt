package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.ExerciseRepository

class RemoveCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository
) : RemoveCategoryUseCase {

    override suspend operator fun invoke(categoryId: String) {
        exerciseRepository.removeCategory(categoryId)
    }
}
