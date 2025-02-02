package pl.msiwak.infrastructure.repositories

import pl.msiwak.infrastructure.service.NotificationService

class NotificationRepository(private val service: NotificationService) {

    suspend fun sendPushNotification(deviceToken: String, title: String, body: String) {
        service.sendPushNotification(deviceToken, title, body)
    }
}
