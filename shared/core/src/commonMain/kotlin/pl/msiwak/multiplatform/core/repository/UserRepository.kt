package pl.msiwak.multiplatform.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.core.api.service.UserService

class UserRepository(private val userService: UserService) {

    suspend fun getUser(): User = withContext(Dispatchers.Default) {
        return@withContext userService.getUser()
    }
}
