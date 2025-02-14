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
        println("MY LOGS user: $user")
        println("MY LOGS DEVICE TOKEN: $deviceToken")

        if (!deviceToken.isNullOrEmpty()) {
            println("MY LOGS DEVICE TOKEN in if: $deviceToken")
            notificationRepository.sendPushNotification(deviceToken, "TEST", "TEST")
        }
    }
}
