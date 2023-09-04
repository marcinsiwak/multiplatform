package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.core.data.common.Category
import pl.msiwak.multiplatform.core.repository.CategoryRepository

class InsertCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryData: List<Category>) {
        return categoryRepository.insertCategories(categoryData)
    }
}
