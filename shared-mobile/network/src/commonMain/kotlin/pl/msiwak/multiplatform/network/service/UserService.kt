package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.api.UserApi
import pl.msiwak.multiplatform.network.mapper.UserMapper

class UserService(
    private val client: UserApi,
    private val mapper: UserMapper
) {

    suspend fun getUser(): User {
        return mapper(client.getUser())
    }

    suspend fun getUsers(): List<User> {
        return client.getUsers().map { users -> mapper(users) }
    }

    suspend fun createUser(uuid: String, email: String) {
        return client.createUser(uuid, email)
    }

    suspend fun createUserWithGoogle() {
        return client.createUserWithGoogle()
    }

//    suspend fun updateUser(uuid: String) {
//        return client.updateUser(uuid)
//    }
}
