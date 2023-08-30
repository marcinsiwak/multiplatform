package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.repository.CategoryRepository

class ObserveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(id: String): Flow<Category> {
        return categoryRepository.observeCategory(id)
    }
}