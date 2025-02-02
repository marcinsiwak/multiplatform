package pl.msiwak.multiplatform.notifications

import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.store.SessionStore

class NotificationsService : FirebaseMessagingService() {

    private val sessionStore: SessionStore by inject(SessionStore::class.java)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sessionStore.saveMessagingToken(token)
    }
}
