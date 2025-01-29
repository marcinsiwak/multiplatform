package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository

class ResendVerificationEmailUseCaseImpl(private val authRepository: AuthRepository) :
    ResendVerificationEmailUseCase {
    override suspend operator fun invoke() {
        authRepository.resendVerificationEmail()
    }
}
