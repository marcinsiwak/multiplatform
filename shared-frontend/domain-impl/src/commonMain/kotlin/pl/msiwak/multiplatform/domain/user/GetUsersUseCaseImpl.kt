package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class GetUsersUseCaseImpl(private val userRepository: UserRepository) : GetUsersUseCase {
    override suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}
