package pl.msiwak.multiplatform.domain.summaries

interface DownloadExerciseUseCase {
    suspend operator fun invoke(id: String)
}
