package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository

interface DeleteUserUseCase {
    suspend operator fun invoke(userId: String): Int
}

class DeleteUserUseCaseImpl(
    private val userRepository: UserRepository
) : DeleteUserUseCase {
    override suspend fun invoke(userId: String): Int {
        return userRepository.deleteUser(userId)
    }
}
