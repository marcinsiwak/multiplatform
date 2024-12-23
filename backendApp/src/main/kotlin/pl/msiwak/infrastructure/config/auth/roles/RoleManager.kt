package pl.msiwak.infrastructure.config.auth.roles

import com.google.firebase.auth.FirebaseAuth

class RoleManager {

    fun setRole(uid: String, role: Role) {
        val claims = hashMapOf<String, Any>().apply {
            this[role.tag] = true
        }
        FirebaseAuth.getInstance().setCustomUserClaims(uid, claims)
    }
}
