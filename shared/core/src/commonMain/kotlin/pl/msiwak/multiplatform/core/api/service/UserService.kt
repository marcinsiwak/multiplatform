package pl.msiwak.multiplatform.core.api.service

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.core.api.client.UserClient
import pl.msiwak.multiplatform.core.data.common.User
import pl.msiwak.multiplatform.core.mapper.UserMapper

class UserService(
    private val client: UserClient,
    private val mapper: UserMapper
) {

    suspend fun getUser(): User {
        return client.getUser().map { mapper(it) }.first()
    }
}