package pl.msiwak.multiplatform.domain.offline

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class SetOfflineModeUseCase(private val sessionRepository: SessionRepository) {

    suspend operator fun invoke(isOffline: Boolean) {
        sessionRepository.setOfflineSession(isOffline)
    }
}