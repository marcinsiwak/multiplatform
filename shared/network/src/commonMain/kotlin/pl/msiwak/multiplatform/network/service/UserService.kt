package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.client.UserClient
import pl.msiwak.multiplatform.network.mapper.UserMapper

class UserService(
    private val client: UserClient,
    private val mapper: UserMapper
) {

    suspend fun getUser(): Flow<User> {
        return client.getUser().map { mapper(it) }
    }
}
