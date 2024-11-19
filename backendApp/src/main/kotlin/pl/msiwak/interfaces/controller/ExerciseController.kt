package pl.msiwak.interfaces.controller

import kotlinx.datetime.Instant
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult

interface ExerciseController {
    suspend fun addCategory(name: String, exerciseType: String, userId: String): ApiCategory
    suspend fun getCategories(userId: String): List<ApiCategory>
    suspend fun getCategory(userId: String): ApiCategory?
    suspend fun removeCategory(categoryId: String)
    suspend fun addExercise(categoryId: String, name: String): ApiExercise?
    suspend fun getExercise(exerciseId: String): ApiExercise?
    suspend fun removeExercise(exerciseId: String)
    suspend fun addResult(exerciseId: String, amount: String, result: String, date: Instant): ApiResult?
    suspend fun removeResult(resultId: String)
    suspend fun synchronizeData(
        categoriesDTO: List<ApiCategory>,
        exercisesDTO: List<ApiExercise>,
        resultsDTO: List<ApiResult>,
        userId: String
    )
}