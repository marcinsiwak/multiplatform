package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class SaveUserTokenUseCase(private val sessionRepository: SessionRepository) {
    suspend operator fun invoke(token: String) {
        sessionRepository.saveToken(token)
    }
}