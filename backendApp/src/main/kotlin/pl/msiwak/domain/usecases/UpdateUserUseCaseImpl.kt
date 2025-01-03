package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

class UpdateUserUseCaseImpl(
    private val userRepository: UserRepository
) : UpdateUserUseCase {
    override suspend operator fun invoke(name: String, email: String, userId: String) {
        userRepository.addUser(userId, name, email)
    }
}
