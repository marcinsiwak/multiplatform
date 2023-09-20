package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class InsertCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryData: List<Category>) {
        return categoryRepository.insertCategories(categoryData)
    }
}
