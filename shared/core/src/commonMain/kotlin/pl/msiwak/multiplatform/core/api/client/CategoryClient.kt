package pl.msiwak.multiplatform.core.api.client

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.core.api.data.user.ApiCategory

class CategoryClient(private val ktorClient: KtorClient) {

    suspend fun downloadCategories(): Flow<List<ApiCategory>> {
        //todo implement urlString
//        val response: List<ApiCategory> =  ktorClient.httpClient.get("Exercises").body()
        return flowOf(emptyList())
    }

    suspend fun createCategory(category: ApiCategory) {
        //todo implement urlString
        ktorClient.httpClient.post("Exercises/CreateCategory") {
            setBody(category)
        }
    }
}
