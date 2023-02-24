package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class InsertCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: List<CategoryData>) {
        return categoryRepository.insertCategory(category)
    }
}
