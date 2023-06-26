package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.domain.data.user.User
import pl.msiwak.multiplatform.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User {
        return userRepository.getUser()
    }
}