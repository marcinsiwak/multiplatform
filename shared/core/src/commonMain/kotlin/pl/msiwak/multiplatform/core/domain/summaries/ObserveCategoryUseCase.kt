package pl.msiwak.multiplatform.core.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.core.data.common.Category
import pl.msiwak.multiplatform.core.repository.CategoryRepository

class ObserveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(id: String): Flow<Category> {
        return categoryRepository.observeCategory(id)
    }
}