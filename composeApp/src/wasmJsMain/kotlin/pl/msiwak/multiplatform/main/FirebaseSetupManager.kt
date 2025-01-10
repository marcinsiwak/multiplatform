package pl.msiwak.multiplatform.main

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class FirebaseSetupManager {

    @JsName("firebaseConfig")
    fun firebaseConfig(): JsonObject {
        return buildJsonObject {
            put("apiKey", "AIzaSyC6NQgwckIiz7L5S7EVLHidsO8IByB3y_E")
            put("authDomain", "sportplatform-b5318.firebaseapp.com")
            put("projectId", "sportplatform-b5318")
            put("storageBucket", "sportplatform-b5318.firebasestorage.app")
            put("messagingSenderId", "607279059338")
            put("appId", "1:607279059338:web:23cf771c199457fdcb3873")
            put("measurementId", "G-N93CDPCXM5")
        }
    }
}
