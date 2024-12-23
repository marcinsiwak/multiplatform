package pl.msiwak.domain.usecases

interface AddUserUseCase {
    suspend operator fun invoke(name: String, email: String, userId: String)
}
