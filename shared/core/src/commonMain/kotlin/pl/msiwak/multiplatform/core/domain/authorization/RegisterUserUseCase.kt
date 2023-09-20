package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.auth.FirebaseAuthorization

class RegisterUserUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke(params: Params) {
        firebaseAuthorization.createNewUser(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}