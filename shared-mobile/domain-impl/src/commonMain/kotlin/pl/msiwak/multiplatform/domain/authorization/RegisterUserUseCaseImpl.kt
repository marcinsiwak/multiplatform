package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository

class RegisterUserUseCaseImpl(private val authRepository: AuthRepository) : RegisterUserUseCase {

    override suspend fun invoke(params: RegisterUserUseCase.Params) {
        authRepository.createNewUser(params.login, params.password)
    }
}
