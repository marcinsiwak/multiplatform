package pl.msiwak.multiplatform.api.service

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.api.client.KtorClient
import pl.msiwak.multiplatform.api.data.user.ApiUser
import pl.msiwak.multiplatform.data.store.SessionStore

class UserService(private val client: KtorClient, private val sessionStore: SessionStore) {

    suspend fun getUser(): Flow<ApiUser> {
        val response: ApiUser =
            client.httpClient.get("https://siwakapi.azurewebsites.net/Sample/UserInfo") {
                contentType(ContentType.Application.Json.withParameter("charset", "utf-8"))
                header("Authorization", "Bearer ${sessionStore.getToken()}")
            }.body()
        return flowOf(response)
    }
}