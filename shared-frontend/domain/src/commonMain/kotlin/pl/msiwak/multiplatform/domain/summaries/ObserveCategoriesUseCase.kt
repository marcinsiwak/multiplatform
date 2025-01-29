package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category

interface ObserveCategoriesUseCase {
    suspend operator fun invoke(): Flow<List<Category>>
}
