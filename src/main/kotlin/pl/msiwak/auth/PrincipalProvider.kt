package pl.msiwak.auth

import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.error.MissingPrincipalError

class PrincipalProvider {
    private var principal: FirebaseUser? = null

    fun setPrincipal(principal: FirebaseUser) {
        println("Setting principal on thread: ${Thread.currentThread().id}")

        this.principal = principal
    }

    fun getPrincipal(): FirebaseUser {
        println("Setting principal on thread: ${Thread.currentThread().id}")

        return principal ?: throw MissingPrincipalError()
    }

    fun clear() {
        this.principal = null
    }
}