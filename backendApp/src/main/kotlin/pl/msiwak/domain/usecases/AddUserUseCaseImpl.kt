package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

class AddUserUseCaseImpl(
    private val userRepository: UserRepository
) : AddUserUseCase {
    override suspend operator fun invoke(userId: String, name: String, email: String) {
        userRepository.addUser(userId, name, email)
    }
}
