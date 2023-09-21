package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(id: String): Flow<Category> {
        return categoryRepository.observeCategory(id)
    }
}