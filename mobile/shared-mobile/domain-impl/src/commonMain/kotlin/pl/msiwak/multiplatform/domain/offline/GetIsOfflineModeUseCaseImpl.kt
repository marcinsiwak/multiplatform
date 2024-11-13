package pl.msiwak.multiplatform.domain.offline

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class GetIsOfflineModeUseCaseImpl(private val sessionRepository: SessionRepository) :
    GetIsOfflineModeUseCase {

    override suspend operator fun invoke() = sessionRepository.getIsOfflineSession()
}
