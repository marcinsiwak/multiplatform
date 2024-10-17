package pl.msiwak.application.usecases

interface AddUserUseCase {
    suspend operator fun invoke(name: String, email: String, userId: String)
}