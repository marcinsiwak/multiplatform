package pl.msiwak.multiplatform.notifications

import com.google.firebase.messaging.FirebaseMessaging
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class NotificationsManager {

    private val firebaseMessaging = FirebaseMessaging.getInstance()

    actual suspend fun getToken() = suspendCoroutine { continuation ->
        firebaseMessaging.token
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                val token = it.result
                continuation.resume(token)
            }
            .addOnFailureListener {
                continuation.resume(null)
            }
    }
}