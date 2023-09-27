package pl.msiwak.multiplatform.network.client

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.model.ApiCategory

class CategoryClient(private val ktorClient: KtorClient) {

    suspend fun downloadCategories(): Flow<List<ApiCategory>> {
        val response: List<ApiCategory> = ktorClient.httpClient.get("Exercises/Categories").body()
        return flowOf(response)
    }

    suspend fun createCategory(category: ApiCategory) {
        ktorClient.httpClient.post("Exercises/CreateCategory") {
            setBody(category)
        }
    }
}
