package pl.msiwak.multiplatform.network.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.network.model.ApiUser

class UserApi(private val ktorClient: KtorClient) {

    suspend fun getUser(): Flow<ApiUser> {
        val response: ApiUser =
            ktorClient.httpClient.get("User/UserInfo").body()
        return flowOf(response)
    }
}
