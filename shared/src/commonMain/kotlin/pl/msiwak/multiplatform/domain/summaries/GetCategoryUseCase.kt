package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(id: Long): CategoryData {
        return categoryRepository.getCategory(id)
    }
}