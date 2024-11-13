package pl.msiwak.interfaces.controller

import pl.msiwak.application.usecases.AddUserUseCase
import pl.msiwak.application.usecases.GetUserUseCase
import pl.msiwak.interfaces.dtos.UserDTO

class UserControllerImpl(
    private val addUserUseCase: AddUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : UserController {

    override suspend fun addUser(name: String, email: String, userId: String) {
        addUserUseCase(name, email, userId)
    }

    override suspend fun getUser(userId: String): UserDTO? {
        return getUserUseCase(userId)
    }
}