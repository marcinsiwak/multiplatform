package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.mapper.CategoryDTOMapper

class GetCategoriesUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val categoryDTOMapper: CategoryDTOMapper
) : GetCategoriesUseCase {
    override suspend operator fun invoke(userId: String): List<CategoryDTO> {
        return exerciseRepository.getCategories(userId)
            .map { category ->
                categoryDTOMapper(category)
            }
    }
}