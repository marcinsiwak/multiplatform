package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.core.api.authorization.FirebaseAuthorization

class ResendVerificationEmailUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke() {
        firebaseAuthorization.resendVerificationEmail()
    }
}
