package pl.msiwak.multiplatform.domain.user

interface CreateUserUseCase {
    suspend operator fun invoke(uuid: String? = null, email: String? = null)
}
