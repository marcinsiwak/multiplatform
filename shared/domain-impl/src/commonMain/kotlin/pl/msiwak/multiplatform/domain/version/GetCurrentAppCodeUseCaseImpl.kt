package pl.msiwak.multiplatform.domain.version

import pl.msiwak.multiplatform.data.remote.repository.VersionRepository

class GetCurrentAppCodeUseCaseImpl(private val versionRepository: VersionRepository) :
    GetCurrentAppCodeUseCase {
    override operator fun invoke(): String {
        return versionRepository.getLongerVersionCode()
    }
}
