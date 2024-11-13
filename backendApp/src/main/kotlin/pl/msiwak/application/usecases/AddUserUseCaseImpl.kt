package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.UserRepository

class AddUserUseCaseImpl(
    private val userRepository: UserRepository
) : AddUserUseCase {
    override suspend operator fun invoke(name: String, email: String, userId: String) {
        userRepository.addUser(userId, name, email)
    }
}
