package pl.msiwak.multiplatform.domain.offline

interface SetOfflineModeUseCase {
    suspend operator fun invoke(isOffline: Boolean)
}
