package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    RemoveCategoryUseCase {
    override suspend operator fun invoke(categoryId: String) {
        categoryRepository.removeCategory(categoryId)
    }
}
