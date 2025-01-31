package pl.msiwak.interfaces.controller

import pl.msiwak.domain.usecases.AddUserUseCase
import pl.msiwak.domain.usecases.GetUserUseCase
import pl.msiwak.domain.usecases.GetUsersUseCase
import pl.msiwak.domain.usecases.UpdateUserUseCase
import pl.msiwak.domain.usecases.notification.RegisterDeviceForNotificationsUseCase
import pl.msiwak.domain.usecases.notification.SendNotificationsUseCase
import pl.msiwak.domain.usecases.notification.UnregisterDeviceForNotificationsUseCase
import pl.msiwak.multiplatform.shared.model.ApiUser

@Suppress("LongParameterList")
class UserControllerImpl(
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val registerDeviceForNotificationsUseCase: RegisterDeviceForNotificationsUseCase,
    private val unregisterDeviceForNotificationsUseCase: UnregisterDeviceForNotificationsUseCase,
    private val sendNotificationsUseCase: SendNotificationsUseCase
) : UserController {

    override suspend fun addUser(userId: String, name: String, email: String) {
        addUserUseCase(userId, name, email)
    }

    override suspend fun updateUser(userId: String, name: String, email: String) {
        updateUserUseCase(userId, name, email)
    }

    override suspend fun getUser(userId: String): ApiUser? {
        return getUserUseCase(userId)
    }

    override suspend fun getUsers(): List<ApiUser> {
        return getUsersUseCase()
    }

    override suspend fun registerUserDeviceForNotification(deviceToken: String, userId: String) {
        registerDeviceForNotificationsUseCase(deviceToken, userId)
    }

    override suspend fun unregisterUserDeviceForNotification(deviceToken: String) {
        unregisterDeviceForNotificationsUseCase(deviceToken)
    }

    override suspend fun sendNotification(userId: String) {
        sendNotificationsUseCase(userId)
    }
}
