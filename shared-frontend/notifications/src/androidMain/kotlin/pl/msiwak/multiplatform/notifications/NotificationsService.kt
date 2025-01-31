package pl.msiwak.multiplatform.notifications

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.store.SessionStore

class NotificationsService : FirebaseMessagingService() {

    private val sessionStore: SessionStore by inject(SessionStore::class.java)

    init {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
            }
        )
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sessionStore.saveMessagingToken(token)
    }
}
