package pl.msiwak.multiplatform.network.client

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.model.ApiCategory
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExercise
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResult
import pl.msiwak.multiplatform.network.model.ApiResultRequest
import pl.msiwak.multiplatform.network.model.ApiUpdateExerciseNameRequest

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

    suspend fun removeCategory(id: String) {
        ktorClient.httpClient.delete("Exercises/Category/$id")
    }

    suspend fun downloadExercise(id: String): Flow<ApiExercise> {
        val response: ApiExercise = ktorClient.httpClient.get("Exercises/Exercise/$id").body()
        return flowOf(response)
    }

    suspend fun addExercise(exercise: ApiExerciseRequest): Flow<ApiExercise> {
        val response: ApiExercise = ktorClient.httpClient.post("Exercises/Exercise") {
            setBody(exercise)
        }.body()
        return flowOf(response)
    }

    suspend fun updateExerciseName(updateExerciseNameRequest: ApiUpdateExerciseNameRequest) {
        ktorClient.httpClient.patch("Exercises/Exercise/Name") {
            setBody(updateExerciseNameRequest)
        }
    }

    suspend fun removeExercise(id: String) {
        ktorClient.httpClient.delete("Exercises/Exercise/$id")
    }

    suspend fun addResult(result: ApiResultRequest): Flow<ApiResult> {
        val response: ApiResult = ktorClient.httpClient.post("Exercises/Result") {
            setBody(result)
        }.body()
        return flowOf(response)
    }

    suspend fun removeResult(id: String) {
        ktorClient.httpClient.delete("Exercises/Result/$id")
    }
}
