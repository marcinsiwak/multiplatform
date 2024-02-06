package pl.msiwak.multiplatform.domain.authorization

import io.github.aakira.napier.Napier
import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class LoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) : LoginUseCase {
    override suspend fun invoke(params: LoginUseCase.Params): Boolean {
        val result = authRepository.login(params.login, params.password)
        val token = result.user?.getIdTokenResult(true)?.token
        val isEmailVerified = result.user?.isEmailVerified ?: false
        if (token != null && isEmailVerified) {
            sessionRepository.saveToken(token)
            sessionRepository.setOfflineSession(false)
            Napier.e("OUTPUT: $token")
        }
        return isEmailVerified
    }
}
