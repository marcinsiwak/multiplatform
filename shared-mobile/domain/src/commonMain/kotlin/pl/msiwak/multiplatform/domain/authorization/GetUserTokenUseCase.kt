package pl.msiwak.multiplatform.domain.authorization

interface GetUserTokenUseCase {
    suspend operator fun invoke(): String?
}
