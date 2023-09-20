package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.auth.FirebaseAuthorization

class ResendVerificationEmailUseCase(private val firebaseAuthorization: FirebaseAuthorization) {
    suspend operator fun invoke() {
        firebaseAuthorization.resendVerificationEmail()
    }
}
