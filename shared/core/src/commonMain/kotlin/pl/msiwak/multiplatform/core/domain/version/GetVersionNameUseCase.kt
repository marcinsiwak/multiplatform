package pl.msiwak.multiplatform.core.domain.version

import pl.msiwak.multiplatform.core.repository.VersionRepository

class GetVersionNameUseCase(private val versionRepository: VersionRepository) {
    operator fun invoke(): String {
        return versionRepository.getVersionName()
    }
}