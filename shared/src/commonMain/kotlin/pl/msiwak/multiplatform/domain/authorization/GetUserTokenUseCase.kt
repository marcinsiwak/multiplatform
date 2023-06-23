package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.SessionRepository

class GetUserTokenUseCase(private val sessionRepository: SessionRepository) {
    suspend operator fun invoke(): String? {
        return sessionRepository.getToken()
    }
}
