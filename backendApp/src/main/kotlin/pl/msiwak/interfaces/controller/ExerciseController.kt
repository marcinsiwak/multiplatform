package pl.msiwak.interfaces.controller

import kotlinx.datetime.Instant
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO

interface ExerciseController {
    suspend fun addCategory(name: String, exerciseType: String, userId: String): CategoryDTO
    suspend fun getCategories(userId: String): List<CategoryDTO>
    suspend fun getCategory(userId: String): CategoryDTO?
    suspend fun removeCategory(categoryId: String)
    suspend fun addExercise(categoryId: String, name: String): ExerciseDTO?
    suspend fun getExercise(exerciseId: String): ExerciseDTO?
    suspend fun removeExercise(exerciseId: String)
    suspend fun addResult(exerciseId: String, amount: String, result: String, date: Instant): ResultDTO?
    suspend fun removeResult(resultId: String)
    suspend fun synchronizeData(
        categoriesDTO: List<CategoryDTO>,
        exercisesDTO: List<ExerciseDTO>,
        resultsDTO: List<ResultDTO>,
        userId: String
    )
}