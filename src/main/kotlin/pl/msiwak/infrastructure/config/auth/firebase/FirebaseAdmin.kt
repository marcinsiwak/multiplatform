package pl.msiwak.infrastructure.config.auth.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.InputStream

object FirebaseAdmin {
    private val serviceAccount: InputStream? = setServiceAccount()
    private val options: FirebaseOptions = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    private fun setServiceAccount(): InputStream? {
        print("MYLOG: HERE")
//        return System.getenv("FIREBASE_SERVICE_ACCOUNT")?.let {
//            print("MYLOG: HERE2: $it")
//            val out = ByteArrayInputStream(it.toByteArray())
//            print("MYLOG: HERE3: $out")
//            out
//        } ?:

        val out = this::class.java.classLoader.getResourceAsStream("sportplatform-b5318-firebase-adminsdk-egpiw-0065c30f75.json")
        print("MYLOG: HERE: $out")

        return out
    }

    fun init(): FirebaseApp = FirebaseApp.initializeApp(options)
}
