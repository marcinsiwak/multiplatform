package pl.msiwak.multiplatform.domain.remoteConfig

import io.github.aakira.napier.Napier
import pl.msiwak.multiplatform.repository.RemoteConfigRepository

class GetMinVersionUseCase(private val remoteConfigRepository: RemoteConfigRepository) {
    operator fun invoke(): String {
        Napier.e("OUTPUT: ${remoteConfigRepository.getMinVersion().asString()}")
        return remoteConfigRepository.getMinVersion().asString()
    }
}