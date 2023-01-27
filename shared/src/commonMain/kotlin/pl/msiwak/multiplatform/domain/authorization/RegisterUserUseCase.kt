package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization

class RegisterUserUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke(params: Params) {
        firebaseAuthorization.createNewUser(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}