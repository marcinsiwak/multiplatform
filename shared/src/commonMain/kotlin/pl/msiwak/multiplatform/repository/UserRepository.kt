package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.api.service.UserService
import pl.msiwak.multiplatform.data.common.User

class UserRepository(private val userService: UserService) {

    suspend fun getUser(): User = withContext(Dispatchers.Default) {
        return@withContext userService.getUser().map {
            User(
                email = it.email,
                userName = it.username
            )
        }.first()
    }
}
