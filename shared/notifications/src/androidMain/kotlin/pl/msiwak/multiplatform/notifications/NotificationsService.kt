package pl.msiwak.multiplatform.notifications

import com.google.firebase.messaging.FirebaseMessagingService

class NotificationsService : FirebaseMessagingService() {

    init {
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w("OUTPUT", "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            // Get new FCM registration token
//            val token = task.result
//
//        })
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}