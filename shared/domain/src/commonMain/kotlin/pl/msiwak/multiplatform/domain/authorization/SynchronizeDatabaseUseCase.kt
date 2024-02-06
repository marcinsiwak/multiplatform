package pl.msiwak.multiplatform.domain.authorization

interface SynchronizeDatabaseUseCase {
    suspend operator fun invoke()
}
