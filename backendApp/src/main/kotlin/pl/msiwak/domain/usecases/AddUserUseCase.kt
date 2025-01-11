package pl.msiwak.domain.usecases

interface AddUserUseCase {
    suspend operator fun invoke(userId: String, name: String, email: String)
}
