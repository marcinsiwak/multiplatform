package pl.msiwak.auth

import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.error.MissingPrincipalError

class PrincipalProvider {
    private val principal = ThreadLocal<FirebaseUser?>()

    fun setPrincipal(principal: FirebaseUser) {
        this.principal.set(principal)
    }

    fun getPrincipal(): FirebaseUser {
        return this.principal.get() ?: throw MissingPrincipalError()
    }

    fun clear() {
        this.principal.remove()
    }
}