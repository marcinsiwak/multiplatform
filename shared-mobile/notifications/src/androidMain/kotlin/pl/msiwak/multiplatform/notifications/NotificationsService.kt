package pl.msiwak.multiplatform.notifications

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class NotificationsService : FirebaseMessagingService() {

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new FCM registration token
            }
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}
