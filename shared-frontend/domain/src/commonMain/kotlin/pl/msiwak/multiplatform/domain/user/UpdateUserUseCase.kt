package pl.msiwak.multiplatform.domain.user

interface UpdateUserUseCase {
    suspend operator fun invoke(uuid: String)
}
