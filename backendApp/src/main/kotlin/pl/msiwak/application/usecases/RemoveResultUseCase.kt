package pl.msiwak.application.usecases

interface RemoveResultUseCase {
    suspend operator fun invoke(resultId: String)
}