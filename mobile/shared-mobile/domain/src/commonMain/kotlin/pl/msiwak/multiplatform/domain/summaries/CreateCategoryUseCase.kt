package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category

interface CreateCategoryUseCase {
    suspend operator fun invoke(category: Category)
}
