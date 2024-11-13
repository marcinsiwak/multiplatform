package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category

interface ObserveCategoryUseCase {
    suspend operator fun invoke(id: String): Flow<Category>
}
