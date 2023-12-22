package pl.msiwak.multiplatform.domain.offline

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class GetIsOfflineModeUseCase(private val sessionRepository: SessionRepository) {

    suspend operator fun invoke() = sessionRepository.getIsOfflineSession()
}
