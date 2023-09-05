package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.core.repository.CategoryRepository

class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(category: Category) {
        categoryRepository.createCategory(category)
    }
}
