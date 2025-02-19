package pl.msiwak.domain.usecases.notification

import pl.msiwak.infrastructure.repositories.NotificationRepository
import pl.msiwak.infrastructure.repositories.UserRepository

class SendNotificationsUseCaseImpl(
    private val userRepository: UserRepository,
    private val notificationRepository: NotificationRepository
) : SendNotificationsUseCase {
    override suspend fun invoke(userId: String) {
        val user = userRepository.getUser(userId)
        val deviceToken = user?.deviceToken

        if (!deviceToken.isNullOrEmpty()) {
            notificationRepository.sendPushNotification(deviceToken, "TEST", "TEST")
        }
    }
}
