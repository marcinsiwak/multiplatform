package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization

class LoginUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke(params: Params) {
        firebaseAuthorization.loginUser(params.login, params.password)
    }

    data class Params(val login: String, val password: String)
}