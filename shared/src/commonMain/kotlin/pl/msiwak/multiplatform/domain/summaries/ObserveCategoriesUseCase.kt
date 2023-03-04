package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.repository.CategoryRepository

class ObserveCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    operator fun invoke(): Flow<List<CategoryData>> {
        return categoryRepository.observeCategories()
    }
}