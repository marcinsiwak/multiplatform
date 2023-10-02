package pl.msiwak.multiplatform.notifications

import cocoapods.FirebaseMessaging.FIRMessaging
import io.github.aakira.napier.Napier

class NotificationService {

    init {
        val token = FIRMessaging.messaging().FCMToken
        // TODO: configure firebase for ios
        Napier.e("OUTPUT: $token")
    }
}
