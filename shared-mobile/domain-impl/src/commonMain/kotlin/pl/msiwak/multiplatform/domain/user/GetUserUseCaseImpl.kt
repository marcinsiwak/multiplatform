package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class GetUserUseCaseImpl(private val userRepository: UserRepository) : GetUserUseCase {
    override suspend operator fun invoke(): User {
        return userRepository.getUser()
    }
}
