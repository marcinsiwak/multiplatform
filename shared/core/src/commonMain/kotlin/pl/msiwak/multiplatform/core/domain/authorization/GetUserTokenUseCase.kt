package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.core.repository.SessionRepository

class GetUserTokenUseCase(private val sessionRepository: SessionRepository) {
    suspend operator fun invoke(): String? {
        return sessionRepository.getToken()
    }
}
