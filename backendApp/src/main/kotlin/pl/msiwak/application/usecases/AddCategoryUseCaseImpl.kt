package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.CategoryEntity
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.mapper.CategoryDTOMapper

class AddCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val categoryDTOMapper: CategoryDTOMapper
) : AddCategoryUseCase {
    override suspend operator fun invoke(name: String, exerciseType: String, userId: String): CategoryDTO {
        val category = CategoryEntity(
            userId = userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
        return categoryDTOMapper(category)
    }
}
