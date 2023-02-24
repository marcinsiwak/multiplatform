package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): List<CategoryData> {
        return categoryRepository.getCategories()
    }
}
