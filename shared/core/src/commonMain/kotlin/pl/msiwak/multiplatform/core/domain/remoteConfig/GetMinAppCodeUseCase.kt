package pl.msiwak.multiplatform.core.domain.remoteConfig

import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository

class GetMinAppCodeUseCase(private val remoteConfigRepository: RemoteConfigRepository) {
    operator fun invoke(): String {
        return remoteConfigRepository.getMinVersion().asString()
    }
}