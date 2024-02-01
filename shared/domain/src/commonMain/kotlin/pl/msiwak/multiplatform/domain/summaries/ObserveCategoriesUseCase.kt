package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): Flow<List<Category>> {
        return categoryRepository.observeCategories()
    }
}
