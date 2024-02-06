package pl.msiwak.multiplatform.domain.remoteConfig

import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository

class FetchRemoteConfigUseCaseImpl(private val remoteConfigRepository: RemoteConfigRepository) :
    FetchRemoteConfigUseCase {
    override suspend operator fun invoke() {
        remoteConfigRepository.fetch()
    }
}
