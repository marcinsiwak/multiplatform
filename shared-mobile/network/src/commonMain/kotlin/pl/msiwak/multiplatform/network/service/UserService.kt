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

    suspend fun getUser(): Flow<User> {
        return client.getUser().map { mapper(it) }
    }

    suspend fun getUsers(): Flow<List<User>> {
        return client.getUsers().map { it.map { mapper(it) } }
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
