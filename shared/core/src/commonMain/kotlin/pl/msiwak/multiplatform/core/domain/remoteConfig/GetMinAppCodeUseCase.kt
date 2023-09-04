package pl.msiwak.multiplatform.core.domain.remoteConfig

import pl.msiwak.multiplatform.core.repository.RemoteConfigRepository

class GetMinAppCodeUseCase(private val remoteConfigRepository: RemoteConfigRepository) {
    operator fun invoke(): String {
        return remoteConfigRepository.getMinVersion().asString()
    }
}