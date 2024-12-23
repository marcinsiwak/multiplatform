package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

class AddUserUseCaseImpl(
    private val userRepository: UserRepository
) : AddUserUseCase {
    override suspend operator fun invoke(name: String, email: String, userId: String) {
        userRepository.addUser(userId, name, email)
    }
}
