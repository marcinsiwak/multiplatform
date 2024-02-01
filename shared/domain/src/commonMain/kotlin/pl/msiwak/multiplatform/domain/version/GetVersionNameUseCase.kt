package pl.msiwak.multiplatform.domain.version

import pl.msiwak.multiplatform.data.remote.repository.VersionRepository

class GetVersionNameUseCase(private val versionRepository: VersionRepository) {
    operator fun invoke(): String {
        return versionRepository.getVersionName()
    }
}
