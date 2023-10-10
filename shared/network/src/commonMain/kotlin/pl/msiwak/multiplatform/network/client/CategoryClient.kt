package pl.msiwak.multiplatform.network.client

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.model.ApiCategory
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest

class CategoryClient(private val ktorClient: KtorClient) {

    suspend fun downloadCategories(): Flow<List<ApiCategory>> {
        val response: List<ApiCategory> = ktorClient.httpClient.get("Exercises/Categories").body()
        return flowOf(response)
    }

    suspend fun downloadCategory(id: String): Flow<ApiCategory> {
        val response: ApiCategory = ktorClient.httpClient.get("Exercises/Category/$id").body()
        return flowOf(response)
    }

    suspend fun createCategory(category: ApiCategoryRequest) {
        ktorClient.httpClient.post("Exercises/Category") {
            setBody(category)
        }
    }

    suspend fun addExercise(category: ApiExerciseRequest) {
        ktorClient.httpClient.post("Exercises/Exercise") {
            setBody(category)
        }
    }

    suspend fun removeExercise(id: String) {
        ktorClient.httpClient.delete("Exercises/Exercise/$id")
    }

    suspend fun removeCategory(id: String) {
        ktorClient.httpClient.delete("Exercises/Category/$id")
    }
}
