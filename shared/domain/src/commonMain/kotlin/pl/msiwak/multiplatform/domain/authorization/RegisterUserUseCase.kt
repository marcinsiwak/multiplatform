package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository

class RegisterUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: Params) {
        authRepository.createNewUser(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}
