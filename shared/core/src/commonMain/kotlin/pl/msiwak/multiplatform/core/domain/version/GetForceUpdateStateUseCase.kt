package pl.msiwak.multiplatform.core.domain.version

import pl.msiwak.multiplatform.core.domain.remoteConfig.GetMinAppCodeUseCase

class GetForceUpdateStateUseCase(
    private val getCurrentAppCodeUseCase: GetCurrentAppCodeUseCase,
    private val getMinAppCodeUseCase: GetMinAppCodeUseCase
) {
    suspend operator fun invoke(): Boolean {
        val currentVersion = getCurrentAppCodeUseCase().toLong()
        val min = getMinAppCodeUseCase().toLong()
        if (currentVersion < min) {
            return true
        }
        return false
    }
}