package pl.msiwak.multiplatform.domain.summaries

interface RemoveCategoryUseCase {
    suspend operator fun invoke(categoryId: String)
}
