package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.repository.CategoryRepository

class UpdateCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: Category) {
        return categoryRepository.updateCategory(category)
    }
}
