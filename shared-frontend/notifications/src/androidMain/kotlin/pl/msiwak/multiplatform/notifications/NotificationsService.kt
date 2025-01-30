package pl.msiwak.multiplatform.notifications

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.network.api.UserApi

class NotificationsService(private val userApi: UserApi) : FirebaseMessagingService() {

    private var scope: Job? = null

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
        scope = CoroutineScope(Dispatchers.IO).launch {
            userApi.registerDeviceForNotifications(token)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope?.cancel()
        scope = null
    }
}
