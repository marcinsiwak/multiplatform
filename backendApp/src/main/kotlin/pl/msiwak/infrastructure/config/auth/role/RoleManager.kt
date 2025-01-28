package pl.msiwak.infrastructure.config.auth.role

import com.google.firebase.auth.FirebaseAuth
import pl.msiwak.multiplatform.shared.common.Role

class RoleManager {

    fun setRole(uid: String, role: Role) {
        val claims = hashMapOf<String, Any>().apply {
            this[role.tag] = true
        }
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims)
    }
}
