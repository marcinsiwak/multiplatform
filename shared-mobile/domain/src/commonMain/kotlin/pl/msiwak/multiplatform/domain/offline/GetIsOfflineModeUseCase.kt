package pl.msiwak.multiplatform.domain.offline

interface GetIsOfflineModeUseCase {
    suspend operator fun invoke(): Boolean
}
