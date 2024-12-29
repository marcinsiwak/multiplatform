package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.domain.user.CreateUserUseCase

class GoogleLoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val createUserUseCase: CreateUserUseCase
) : GoogleLoginUseCase {
    override suspend operator fun invoke(tokenId: String?, accessToken: String?): String? {
        val result = authRepository.loginWithGoogle(tokenId, accessToken)
        val token = result?.user?.token
        token?.let { sessionRepository.saveToken(it) }
        if (result?.user?.isNewUser == true) {
            result.user?.uid?.let { createUserUseCase(it) }
        }
        println("OUTPUT USER: ${result?.user}")
        return result?.user?.uid
    }
}
