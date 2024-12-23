package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

class AddUserUseCaseImpl(
    private val userRepository: UserRepository
) : pl.msiwak.domain.usecases.AddUserUseCase {
    override suspend operator fun invoke(name: String, email: String, userId: String) {
        userRepository.addUser(userId, name, email)
    }
}
