package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.domain.repositories.ExerciseRepository

class AddCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
) : AddCategoryUseCase {
    override suspend operator fun invoke(name: String, exerciseType: String, userId: String) {
        val category = CategoryEntity(
            userId = userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
    }
}
