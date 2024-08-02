package pl.msiwak.queries

import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.repositories.CategoryRepository

class GetCategoryQueryImpl(private val categoryRepository: CategoryRepository) : GetCategoryQuery {
    override suspend fun invoke(id: String): CategoryDTO? {
        val categoryEntity = categoryRepository.getCategory(id) ?: return null
        return with(categoryEntity) {
            CategoryDTO(
                id = id,
                name = name,
                exerciseType = exerciseType
            )
        }
    }
}