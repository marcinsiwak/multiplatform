package pl.msiwak.multiplatform.network.api

import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.network.model.ApiCategory
import pl.msiwak.multiplatform.network.model.ApiExercise
import pl.msiwak.multiplatform.network.model.ApiResult
import pl.msiwak.multiplatform.network.model.ApiSynchronizationRequest
import pl.msiwak.multiplatform.network.model.ApiUpdateExerciseNameRequest

class CategoryApi(private val ktorClient: KtorClient) {

    suspend fun downloadCategories(): Flow<List<ApiCategory>> {
        val response: List<ApiCategory> = ktorClient.httpClient.get("api/categories").body()
        return flowOf(response)
    }

    suspend fun downloadCategory(id: String): Flow<ApiCategory> {
        val response: ApiCategory = ktorClient.httpClient.get("api/category/$id").body()
        return flowOf(response)
    }

    suspend fun createCategory(category: ApiCategory) {
        ktorClient.httpClient.post("api/category") {
            setBody(category)
        }
    }

    suspend fun removeCategory(id: String) {
        ktorClient.httpClient.delete("api/category/$id")
    }

    suspend fun downloadExercise(id: String): Flow<ApiExercise> = flow {
        val response: ApiExercise = ktorClient.httpClient.get("api/exercise/$id").body()
        emit(response)
    }

    suspend fun addExercise(exercise: ApiExercise): Flow<ApiExercise> {
        val response: ApiExercise = ktorClient.httpClient.post("api/exercise") {
            setBody(exercise)
        }.body()
        return flowOf(response)
    }

    suspend fun updateExerciseName(updateExerciseNameRequest: ApiUpdateExerciseNameRequest) {
        ktorClient.httpClient.patch("api/exercise") {
            setBody(updateExerciseNameRequest)
        }
    }

    suspend fun removeExercise(id: String) {
        ktorClient.httpClient.delete("api/exercise/$id")
    }

    suspend fun addResult(result: ApiResult): Flow<ApiResult> {
        val response: ApiResult = ktorClient.httpClient.post("api/result") {
            setBody(result)
        }.body()
        return flowOf(response)
    }

    suspend fun removeResult(id: String) {
        ktorClient.httpClient.delete("api/result/$id")
    }

    suspend fun startInitialSynchronization(synchronizationRequest: ApiSynchronizationRequest) {
        ktorClient.httpClient.post("api/synchronize") {
            setBody(synchronizationRequest)
        }
    }
}
