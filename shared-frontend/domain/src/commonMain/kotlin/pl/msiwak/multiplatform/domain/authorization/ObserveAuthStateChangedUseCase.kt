package pl.msiwak.multiplatform.domain.authorization

interface ObserveAuthStateChangedUseCase {
    suspend operator fun invoke()
}
