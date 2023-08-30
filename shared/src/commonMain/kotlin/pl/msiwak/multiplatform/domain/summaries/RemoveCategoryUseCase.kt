package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.repository.CategoryRepository

class RemoveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String) {
        categoryRepository.removeCategory(categoryId)
    }
}
