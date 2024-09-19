package pl.msiwak.multiplatform.domain.remoteConfig

interface FetchRemoteConfigUseCase {
    suspend operator fun invoke()
}
