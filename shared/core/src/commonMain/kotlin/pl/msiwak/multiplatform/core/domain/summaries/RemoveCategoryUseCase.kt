package pl.msiwak.multiplatform.core.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(categoryId: String) {
        categoryRepository.removeCategory(categoryId)
    }
}
