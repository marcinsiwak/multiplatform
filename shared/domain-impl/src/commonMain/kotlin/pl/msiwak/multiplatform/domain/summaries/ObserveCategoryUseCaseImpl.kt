package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    ObserveCategoryUseCase {
    override suspend operator fun invoke(id: String): Flow<Category> {
        return categoryRepository.observeCategory(id)
    }
}
