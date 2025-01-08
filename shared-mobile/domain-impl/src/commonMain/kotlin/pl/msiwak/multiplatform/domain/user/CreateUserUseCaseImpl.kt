package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class CreateUserUseCaseImpl(private val userRepository: UserRepository) : CreateUserUseCase {
    override suspend fun invoke(uuid: String?, email: String?) {
        userRepository.createUser(uuid, email)
    }
}
