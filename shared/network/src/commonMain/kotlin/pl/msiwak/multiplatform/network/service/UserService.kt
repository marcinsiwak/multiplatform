package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.client.UserClient
import pl.msiwak.multiplatform.network.mapper.UserMapper

class UserService(
    private val client: UserClient,
    private val mapper: UserMapper
) {

    suspend fun getUser(): User {
        return client.getUser().map { mapper(it) }.first()
    }
}