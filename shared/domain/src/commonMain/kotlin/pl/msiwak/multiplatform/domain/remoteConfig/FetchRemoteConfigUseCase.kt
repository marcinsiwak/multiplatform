package pl.msiwak.multiplatform.domain.remoteConfig

import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository

class FetchRemoteConfigUseCase(private val remoteConfigRepository: RemoteConfigRepository) {
    suspend operator fun invoke() {
        remoteConfigRepository.fetch()
    }
}
