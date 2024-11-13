package pl.msiwak.multiplatform.notifications

import cocoapods.FirebaseMessaging.FIRMessaging
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
class NotificationService {

    init {
//        val token = FIRMessaging.messaging().FCMToken
        // todo needs apple developer
        FIRMessaging.messaging().tokenWithCompletion { token, nsError ->
        }
    }
}
