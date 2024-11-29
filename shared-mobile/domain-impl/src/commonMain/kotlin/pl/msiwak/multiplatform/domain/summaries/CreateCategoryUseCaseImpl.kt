package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class CreateCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    CreateCategoryUseCase {
    override suspend operator fun invoke(category: Category) {
        categoryRepository.createCategory(category)
    }
}
