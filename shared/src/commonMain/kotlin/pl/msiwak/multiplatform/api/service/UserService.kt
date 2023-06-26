package pl.msiwak.multiplatform.api.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.api.client.KtorClient
import pl.msiwak.multiplatform.api.data.user.ApiUser

class UserService(private val client: KtorClient) {

    suspend fun getUser(): Flow<ApiUser> {
        val response: ApiUser = client.httpClient.get("Sample/UserInfo").body()
        return flowOf(response)
    }
}