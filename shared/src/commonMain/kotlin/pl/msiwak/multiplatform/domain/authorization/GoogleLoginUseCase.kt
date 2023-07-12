package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.repository.SessionRepository

class GoogleLoginUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(tokenId: String?, accessToken: String?) {
        val token = authRepository.loginWithGoogle(tokenId, accessToken)
        token?.let { sessionRepository.saveToken(it) }
    }
}