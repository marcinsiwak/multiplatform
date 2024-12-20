package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.service.UserService

class UserRepository(private val userService: UserService) {

    suspend fun getUser(): User = withContext(Dispatchers.IO) {
        return@withContext userService.getUser().first()
    }

    suspend fun createUser(uuid: String) = withContext(Dispatchers.IO) {
        return@withContext userService.createUser(uuid)
    }
}
