package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class InsertCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: CategoryData) {
        categoryRepository.insertCategory(category)
    }
}
