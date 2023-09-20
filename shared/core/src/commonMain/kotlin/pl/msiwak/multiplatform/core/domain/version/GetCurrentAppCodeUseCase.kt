package pl.msiwak.multiplatform.core.domain.version

import pl.msiwak.multiplatform.data.remote.repository.VersionRepository

class GetCurrentAppCodeUseCase(private val versionRepository: VersionRepository) {
    operator fun invoke(): String {
        return versionRepository.getLongerVersionCode()
    }
}