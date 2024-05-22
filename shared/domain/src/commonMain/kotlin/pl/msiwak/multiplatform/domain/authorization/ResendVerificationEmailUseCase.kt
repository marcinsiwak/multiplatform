package pl.msiwak.multiplatform.domain.authorization

interface ResendVerificationEmailUseCase {
    suspend operator fun invoke()
}
