package pl.msiwak.multiplatform.core.domain.user

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.core.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): User {
        return userRepository.getUser()
    }
}