package pl.msiwak.multiplatform.domain.version

import pl.msiwak.multiplatform.repository.VersionRepository

class GetCurrentAppCodeUseCase(private val versionRepository: VersionRepository) {
    operator fun invoke(): String {
        return versionRepository.getLongerVersionCode()
    }
}