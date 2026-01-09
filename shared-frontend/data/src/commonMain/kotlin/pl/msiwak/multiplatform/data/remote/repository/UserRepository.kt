package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.commonObject.dispatcher.Dispatchers
import pl.msiwak.multiplatform.network.service.UserService

class UserRepository(private val userService: UserService) {

    suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        return@withContext userService.getUsers()
    }

    suspend fun getUser(): User = withContext(Dispatchers.IO) {
        return@withContext userService.getUser()
    }

    suspend fun deleteUser() = withContext(Dispatchers.IO) {
        userService.deleteUser()
    }

    suspend fun createUser(uuid: String?, email: String?) = withContext(Dispatchers.IO) {
        if (uuid == null || email == null) {
            return@withContext userService.createUserWithGoogle()
        }
        return@withContext userService.createUser(uuid, email)
    }

    suspend fun sendNotifications(user: User) {
        userService.sendNotifications(user)
    }
}
