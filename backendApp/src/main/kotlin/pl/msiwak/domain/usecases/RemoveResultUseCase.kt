package pl.msiwak.domain.usecases

interface RemoveResultUseCase {
    suspend operator fun invoke(resultId: String)
}
