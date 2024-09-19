package pl.msiwak.multiplatform.domain.authorization

interface SaveUserTokenUseCase {
    suspend operator fun invoke(token: String)
}
