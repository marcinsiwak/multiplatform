package pl.msiwak.multiplatform.domain.version

import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCase

class GetForceUpdateStateUseCaseImpl(
    private val getCurrentAppCodeUseCase: GetCurrentAppCodeUseCase,
    private val getMinAppCodeUseCase: GetMinAppCodeUseCase
) : GetForceUpdateStateUseCase {
    override suspend operator fun invoke(): Boolean {
        val currentVersion = getCurrentAppCodeUseCase().toLong()
        val min = getMinAppCodeUseCase().toLong()
        return currentVersion < min
    }
}
