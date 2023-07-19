package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.api.authorization.FirebaseAuthorization

class ResendVerificationEmailUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke() {
        firebaseAuthorization.resendVerificationEmail()
    }
}
