package pl.msiwak.domain.usecases

interface UpdateUserUseCase {
    suspend operator fun invoke(userId: String, name: String, email: String)
}
