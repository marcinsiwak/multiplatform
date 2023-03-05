package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.repository.CategoryRepository

class RemoveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: Long) {
        categoryRepository.removeCategory(categoryId)
    }
}
