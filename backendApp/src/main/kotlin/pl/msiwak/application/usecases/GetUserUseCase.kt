package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.UserDTO

interface GetUserUseCase {
    suspend operator fun invoke(userId: String): UserDTO?
}