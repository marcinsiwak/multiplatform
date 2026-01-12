package pl.msiwak.multiplatform.network.service

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
    suspend fun deleteUser() {
        client.deleteUser()
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

    suspend fun registerDeviceForNotifications(deviceToken: String) {
        client.registerDeviceForNotifications(deviceToken)
    }

    suspend fun unregisterDeviceForNotifications(deviceToken: String) {
        client.unregisterDeviceForNotifications(deviceToken)
    }

    suspend fun sendNotifications(user: User) {
        client.sendNotifications(user)
    }

//    suspend fun updateUser(uuid: String) {
//        return client.updateUser(uuid)
//    }
}
