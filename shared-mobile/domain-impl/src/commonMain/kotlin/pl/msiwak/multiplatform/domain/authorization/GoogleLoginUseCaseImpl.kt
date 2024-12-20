package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class GoogleLoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) : GoogleLoginUseCase {
    override suspend operator fun invoke(tokenId: String?, accessToken: String?): String? {
        val result = authRepository.loginWithGoogle(tokenId, accessToken)
        val token = result?.user?.getIdTokenResult(true)?.token
        token?.let { sessionRepository.saveToken(it) }
        return result?.user?.uid
    }
}
