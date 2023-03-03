package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class ObserveCategoryUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(id: Long): Flow<CategoryData> {
        return categoryRepository.observeCategory(id)
    }
}