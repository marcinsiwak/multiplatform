package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class UpdateCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: CategoryData) {
        return categoryRepository.updateCategory(category)
    }
}
