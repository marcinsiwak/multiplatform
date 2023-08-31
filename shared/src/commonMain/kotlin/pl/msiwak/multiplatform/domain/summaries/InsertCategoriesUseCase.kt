package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.repository.CategoryRepository

class InsertCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryData: List<Category>) {
        return categoryRepository.insertCategories(categoryData)
    }
}
