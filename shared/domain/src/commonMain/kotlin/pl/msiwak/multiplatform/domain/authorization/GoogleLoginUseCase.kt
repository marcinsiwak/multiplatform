package pl.msiwak.multiplatform.domain.authorization

interface GoogleLoginUseCase {
    suspend operator fun invoke(tokenId: String?, accessToken: String?)
}
