package pl.msiwak.multiplatform.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

class FirebaseAuthorizationImpl : FirebaseAuthorization {

    private val auth = Firebase.auth

    override suspend fun createNewUser(email: String, password: String): AuthResult {
        return auth.createUserWithEmailAndPassword(email, password).let {
            it.user?.sendEmailVerification()
            AuthResult(
                user = FirebaseUser(
                    uid = it.user?.uid,
                    email = it.user?.email,
                    displayName = it.user?.displayName,
                    isEmailVerified = it.user?.isEmailVerified ?: false,
                    token = it.user?.getIdToken(true)
                )
            )
        }
    }

    override suspend fun loginUser(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password).let {
            AuthResult(
                user = FirebaseUser(
                    uid = it.user?.uid,
                    email = it.user?.email,
                    displayName = it.user?.displayName,
                    isEmailVerified = it.user?.isEmailVerified ?: false,
                    token = it.user?.getIdToken(true)
                )
            )
        }
    }

    override suspend fun loginWithGoogle(googleToken: String?, accessToken: String?): AuthResult {
        return auth.signInWithCredential(
            authCredential = GoogleAuthProvider.credential(
                idToken = googleToken,
                accessToken = accessToken
            )
        ).let {
            AuthResult(
                user = FirebaseUser(
                    uid = it.user?.uid,
                    email = it.user?.email,
                    displayName = it.user?.displayName,
                    isEmailVerified = it.user?.isEmailVerified ?: false,
                    token = it.user?.getIdToken(true),
                    isNewUser = it.additionalUserInfo?.isNewUser
                )
            )
        }
    }

    override fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        return auth.authStateChanged.map {
            FirebaseUser(
                it?.uid,
                it?.email,
                it?.displayName,
                it?.isEmailVerified ?: false,
                token = it?.getIdToken(false)
            )
        }
    }

    override suspend fun logoutUser() {
        auth.signOut()
    }

    override suspend fun resendVerificationEmail() {
        auth.currentUser?.sendEmailVerification()
    }
}
