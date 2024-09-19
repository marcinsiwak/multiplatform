package pl.msiwak.multiplatform.domain.version

import pl.msiwak.multiplatform.data.remote.repository.VersionRepository

class GetVersionNameUseCaseImpl(private val versionRepository: VersionRepository) :
    GetVersionNameUseCase {
    override operator fun invoke(): String {
        return versionRepository.getVersionName()
    }
}
