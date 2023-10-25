package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository

class ResendVerificationEmailUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.resendVerificationEmail()
    }
}
