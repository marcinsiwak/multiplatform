package pl.msiwak.multiplatform.domain.offline

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class SetOfflineModeUseCaseImpl(private val sessionRepository: SessionRepository) :
    SetOfflineModeUseCase {

    override suspend operator fun invoke(isOffline: Boolean) {
        sessionRepository.setOfflineSession(isOffline)
    }
}
