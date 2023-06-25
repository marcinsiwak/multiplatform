package pl.msiwak.multiplatform.api.authorization

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FirebaseAuthorization {

    private val auth = Firebase.auth

    suspend fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun loginUser(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password)
    }

    fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        return auth.authStateChanged
    }

    suspend fun logoutUser() {
        auth.signOut()
    }

}