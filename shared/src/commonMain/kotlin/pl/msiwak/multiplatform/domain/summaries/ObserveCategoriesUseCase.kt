package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.repository.CategoryRepository

class ObserveCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> {
        return categoryRepository.observeCategories()
    }
}