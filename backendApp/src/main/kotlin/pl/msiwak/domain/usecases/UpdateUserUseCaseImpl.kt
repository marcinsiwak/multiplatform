package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

class UpdateUserUseCaseImpl(
    private val userRepository: UserRepository
) : UpdateUserUseCase {
    override suspend operator fun invoke(userId: String, name: String, email: String) {
        userRepository.addUser(userId, name, email)
    }
}
