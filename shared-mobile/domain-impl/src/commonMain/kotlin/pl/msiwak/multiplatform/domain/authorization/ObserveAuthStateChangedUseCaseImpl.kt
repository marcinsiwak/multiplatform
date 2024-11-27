package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class ObserveAuthStateChangedUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) : ObserveAuthStateChangedUseCase {
    override suspend operator fun invoke() {
        authRepository.observeAuthStateChanged().collect {
            if (it != null && it.isEmailVerified) {
                it.getIdToken(true)?.let { token ->
                    sessionRepository.saveToken(token)
                }
            } else {
                sessionRepository.clearToken()
            }
        }
    }
}
