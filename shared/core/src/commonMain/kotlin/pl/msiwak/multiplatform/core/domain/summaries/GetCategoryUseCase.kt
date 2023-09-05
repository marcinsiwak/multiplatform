package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.core.repository.CategoryRepository

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: String): Category {
        return categoryRepository.getCategory(id)
    }
}