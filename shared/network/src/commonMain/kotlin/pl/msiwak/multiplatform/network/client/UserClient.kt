package pl.msiwak.multiplatform.network.client

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.model.ApiUser

class UserClient(private val ktorClient: KtorClient) {

    suspend fun getUser(): Flow<ApiUser> {
        val response: ApiUser =
            ktorClient.httpClient.get("/User/UserInfo").body()
        return flowOf(response)
    }
}
