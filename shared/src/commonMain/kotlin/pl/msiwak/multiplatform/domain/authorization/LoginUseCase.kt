package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository
import pl.msiwak.multiplatform.repository.SessionRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(params: Params) {
//        val token = authRepository.login(params.login, params.password)
//        token?.let { sessionRepository.saveToken(it) }
    }

    data class Params(val login: String, val password: String)
}