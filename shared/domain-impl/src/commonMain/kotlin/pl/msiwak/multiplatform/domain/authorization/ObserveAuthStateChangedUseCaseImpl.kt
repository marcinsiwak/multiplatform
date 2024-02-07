package pl.msiwak.multiplatform.domain.authorization

import io.github.aakira.napier.Napier
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
                    Napier.e("Output token: $token")
                    // todo: REMOVE TOKEN LOGGER
                    sessionRepository.saveToken(token)
                    sessionRepository.setOfflineSession(false)
                }
            } else {
                sessionRepository.setOfflineSession(true)
                sessionRepository.clearToken()
            }
        }
    }
}
