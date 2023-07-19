package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.repository.SessionRepository

class ObserveAuthStateChangedUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        authRepository.observeAuthStateChanged().collect {
            if (it != null && it.isEmailVerified) {
                it.getIdToken(true)?.let { token -> sessionRepository.saveToken(token) }
            } else {
                sessionRepository.clearToken()
            }
        }
    }
}
