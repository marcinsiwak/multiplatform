package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.core.repository.AuthRepository
import pl.msiwak.multiplatform.core.repository.SessionRepository

class GoogleLoginUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(tokenId: String?, accessToken: String?) {
        val token = authRepository.loginWithGoogle(tokenId, accessToken)
        token?.let { sessionRepository.saveToken(it) }
    }
}