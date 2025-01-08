package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.domain.user.CreateUserUseCase

class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository,
    private val createUserUseCase: CreateUserUseCase,
) : RegisterUserUseCase {

    override suspend fun invoke(params: RegisterUserUseCase.Params) {
        authRepository.createNewUser(params.login, params.password).also {
            createUserUseCase(it.user?.uid, it.user?.email)
        }
    }
}
