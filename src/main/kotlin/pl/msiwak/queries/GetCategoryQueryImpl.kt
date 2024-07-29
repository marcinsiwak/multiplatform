package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.repositories.ExerciseRepository

class GetCategoryQueryImpl(private val exerciseRepository: ExerciseRepository) : GetCategoryQuery {
    override suspend fun invoke(id: String): CategoryDTO? {
        val categoryEntity = exerciseRepository.getCategory(id) ?: return null
        return with(categoryEntity) {
            CategoryDTO(
                id = id,
                name = name,
                exerciseType = exerciseType
            )
        }
    }
}