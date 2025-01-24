package pl.msiwak.infrastructure.config.auth.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.InputStream

object FirebaseAdmin {
    fun init(isDevMode: Boolean): FirebaseApp {
        val serviceAccount: InputStream? =
            this::class.java.classLoader?.getResourceAsStream(getFirebaseAdminSkdFile(isDevMode))
        val options: FirebaseOptions = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()
        return FirebaseApp.initializeApp(options)
    }

    private fun getFirebaseAdminSkdFile(isDevMode: Boolean): String {
        return if (isDevMode) {
            "production/firebase-adminsdk.json"
        } else {
            "dev/firebase-adminsdk.json"
        }
    }
}
