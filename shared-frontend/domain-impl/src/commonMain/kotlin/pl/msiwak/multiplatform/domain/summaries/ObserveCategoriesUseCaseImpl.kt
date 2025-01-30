package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class ObserveCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) :
    ObserveCategoriesUseCase {
    override suspend operator fun invoke(): Flow<List<Category>> {
        return categoryRepository.observeCategories()
    }
}
