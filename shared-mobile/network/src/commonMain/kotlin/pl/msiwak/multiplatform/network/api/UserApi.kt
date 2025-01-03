package pl.msiwak.multiplatform.network.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.shared.model.ApiUser

class UserApi(private val ktorClient: KtorClient) {

    suspend fun getUser(): Flow<ApiUser> {
        val response: ApiUser = ktorClient.httpClient.get("api/user").body()
        return flowOf(response)
    }

    suspend fun createUser(uuid: String) {
        ktorClient.httpClient.post("api/user") {
            setBody(ApiUser(id = uuid))
        }
    }

    suspend fun updateUser(uuid: String) {
        ktorClient.httpClient.put("api/user") {
            setBody(ApiUser(id = uuid))
        }
    }
}
