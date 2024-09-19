package pl.msiwak.multiplatform.domain.version

interface GetForceUpdateStateUseCase {
    suspend operator fun invoke(): Boolean
}
