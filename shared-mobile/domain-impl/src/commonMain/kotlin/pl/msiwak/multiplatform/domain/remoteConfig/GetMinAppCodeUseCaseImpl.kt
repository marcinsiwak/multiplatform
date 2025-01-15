package pl.msiwak.multiplatform.domain.remoteConfig

import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository

class GetMinAppCodeUseCaseImpl(private val remoteConfigRepository: RemoteConfigRepository) :
    GetMinAppCodeUseCase {
    override operator fun invoke(): String {
        return remoteConfigRepository.getMinVersion()
    }
}
