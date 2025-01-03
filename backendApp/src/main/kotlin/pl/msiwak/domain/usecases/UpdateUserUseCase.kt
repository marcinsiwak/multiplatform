package pl.msiwak.domain.usecases

interface UpdateUserUseCase {
    suspend operator fun invoke(name: String, email: String, userId: String)
}
