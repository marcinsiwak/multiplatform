package pl.msiwak.infrastructure.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification

class NotificationService {

    private val messaging = FirebaseMessaging.getInstance()

    fun sendPushNotification(deviceToken: String, title: String, body: String) {
        val notification = Notification.builder()
            .setTitle(title)
            .setBody(body)
            .build()
        val message = Message.builder()
            .setToken(deviceToken)
            .setNotification(notification)
            .build()

        messaging.send(message)
    }
}
