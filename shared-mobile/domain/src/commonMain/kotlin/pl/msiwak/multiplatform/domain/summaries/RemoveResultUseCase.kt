package pl.msiwak.multiplatform.domain.summaries

interface RemoveResultUseCase {
    suspend operator fun invoke(id: String)
}
