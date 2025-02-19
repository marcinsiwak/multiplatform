package pl.msiwak.domain.usecases.notification

import pl.msiwak.infrastructure.repositories.UserRepository
import pl.msiwak.multiplatform.shared.exception.UserNotFoundException

class UnregisterDeviceForNotificationsUseCaseImpl(private val userRepository: UserRepository) :
    UnregisterDeviceForNotificationsUseCase {
    override suspend fun invoke(deviceToken: String) {
        val user = userRepository.getUserByToken(deviceToken)?.copy(deviceToken = null) ?: throw UserNotFoundException()
        userRepository.updateUser(user)
    }
}
