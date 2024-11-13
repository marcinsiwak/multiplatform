package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.mapper.CategoryDTOMapper

class GetCategoryUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val categoryDTOMapper: CategoryDTOMapper
) : GetCategoryUseCase {
    override suspend operator fun invoke(id: String): CategoryDTO? {
        val categoryEntity = exerciseRepository.getCategory(id) ?: return null
        return categoryDTOMapper(categoryEntity)
    }
}