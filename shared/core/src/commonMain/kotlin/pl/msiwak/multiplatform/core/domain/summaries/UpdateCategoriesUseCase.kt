package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class UpdateCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: Category) {
        return categoryRepository.updateCategory(category)
    }
}
