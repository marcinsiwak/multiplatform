package pl.msiwak.multiplatform.domain.summaries

interface DownloadCategoryUseCase {
    suspend operator fun invoke(id: String)
}
