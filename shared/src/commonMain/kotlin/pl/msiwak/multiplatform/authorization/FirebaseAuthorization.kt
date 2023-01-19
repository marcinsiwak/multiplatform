package pl.msiwak.multiplatform.authorization

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth


class FirebaseAuthorization {

    private val auth = Firebase.auth

    suspend fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

}