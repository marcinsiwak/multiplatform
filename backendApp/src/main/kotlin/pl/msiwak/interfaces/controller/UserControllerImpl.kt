package pl.msiwak.interfaces.controller

import pl.msiwak.domain.usecases.AddUserUseCase
import pl.msiwak.domain.usecases.GetUserUseCase
import pl.msiwak.multiplatform.shared.model.ApiUser

class UserControllerImpl(
    private val addUserUseCase: AddUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : UserController {

    override suspend fun addUser(name: String, email: String, userId: String) {
        addUserUseCase(name, email, userId)
    }

    override suspend fun getUser(userId: String): ApiUser? {
        return getUserUseCase(userId)
    }
}
