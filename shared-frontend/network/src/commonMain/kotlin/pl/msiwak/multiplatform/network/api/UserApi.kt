package pl.msiwak.multiplatform.network.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.shared.model.ApiDeviceToken
import pl.msiwak.multiplatform.shared.model.ApiUser

class UserApi(private val ktorClient: KtorClient) {

    suspend fun getUsers(): List<ApiUser> {
        val response: List<ApiUser> = ktorClient.httpClient.get("api/users").body()
        return response
    }

    suspend fun getUser(): ApiUser {
        val response: ApiUser = ktorClient.httpClient.get("api/user").body()
        return response
    }

    suspend fun createUserWithGoogle() {
        ktorClient.httpClient.post("api/googleUser")
    }

    suspend fun createUser(uuid: String, email: String) {
        ktorClient.httpClient.post("api/user") {
            setBody(ApiUser(id = uuid, email = email))
        }
    }

    suspend fun registerDeviceForNotifications(deviceToken: String) {
        ktorClient.httpClient.post("api/user/notification") {
            setBody(ApiDeviceToken(token = deviceToken))
        }
    }

    suspend fun sendNotifications(user: User) {
        ktorClient.httpClient.post("api/user/sendNotification") {
            setBody(ApiUser(id = user.id, email = user.email, user.userName, user.role))
        }
    }

//    suspend fun updateUser(uuid: String) {}
}
