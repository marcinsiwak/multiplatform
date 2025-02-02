package pl.msiwak.domain.usecases.notification

import pl.msiwak.infrastructure.repositories.UserRepository
import pl.msiwak.multiplatform.shared.exception.UserNotFoundException

class RegisterDeviceForNotificationsUseCaseImpl(private val userRepository: UserRepository) :
    RegisterDeviceForNotificationsUseCase {
    override suspend fun invoke(deviceToken: String, userId: String) {
        val user = userRepository.getUser(userId)?.copy(deviceToken = deviceToken) ?: throw UserNotFoundException()
        userRepository.updateUser(user)
    }
}
