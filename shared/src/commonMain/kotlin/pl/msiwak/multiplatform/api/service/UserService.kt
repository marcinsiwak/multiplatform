package pl.msiwak.multiplatform.api.service

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.api.client.UserClient
import pl.msiwak.multiplatform.api.data.user.ApiUser

class UserService(private val client: UserClient) {

    suspend fun getUser(): Flow<ApiUser> {
        return client.getUser()
    }
}