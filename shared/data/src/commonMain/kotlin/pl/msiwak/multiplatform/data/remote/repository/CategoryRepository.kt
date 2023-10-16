package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResultRequest
import pl.msiwak.multiplatform.network.service.CategoryService

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val exercisesDao: ExercisesDao,
    private val categoryService: CategoryService
) {

    suspend fun downloadCategories(): List<Category> = withContext(Dispatchers.Default) {
        val categories = categoryService.downloadCategories()
        categoriesDao.removeAllCategories()
        categoriesDao.updateCategories(categories)
        return@withContext categories
    }

    suspend fun downloadCategory(id: String): Category = withContext(Dispatchers.Default) {
        val category = categoryService.downloadCategory(id)
        categoriesDao.updateCategory(category)
        exercisesDao.updateExercises(category.exercises)
        return@withContext category
    }

    suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.observeCategory(id)
    }

    suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.observeCategories()
    }

    suspend fun insertCategories(categories: List<Category>) =
        withContext(Dispatchers.Default) {
            categoriesDao.insertCategories(categories)
        }

    suspend fun createCategory(category: Category) = withContext(Dispatchers.Default) {
        categoryService.createCategory(
            ApiCategoryRequest(
                name = category.name,
                exerciseType = category.exerciseType.name,
            )
        )
    }

    suspend fun updateCategory(category: Category) = withContext(Dispatchers.Default) {
        categoriesDao.updateCategory(category)
    }

    suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.Default) {
        categoryService.removeCategory(categoryId)
        categoriesDao.removeCategory(categoryId)
    }

    suspend fun downloadExercise(exerciseId: String) = withContext(Dispatchers.Default) {
        categoryService.downloadExercise(exerciseId)
    }

    suspend fun observeExercise(exerciseId: String): Flow<Exercise> = withContext(Dispatchers.Default) {
        return@withContext exercisesDao.observeExercise(exerciseId)
    }

    suspend fun addExercise(exercise: Exercise) = withContext(Dispatchers.Default) {
        categoryService.addExercise(
            ApiExerciseRequest(
                categoryId = exercise.categoryId,
                name = exercise.exerciseTitle
            )
        )
        val category = categoryService.downloadCategory(exercise.categoryId)
        categoriesDao.updateCategory(category)
        exercisesDao.updateExercises(category.exercises)
    }

    suspend fun removeExercise(exercise: Exercise) = withContext(Dispatchers.Default) {
        categoryService.removeExercise(exercise.id)
        exercisesDao.removeExercise(exercise)
    }

    suspend fun addResult(result: ResultData) = withContext(Dispatchers.Default) {
        categoryService.addResult(
            ApiResultRequest(result.result, result.amount.toDouble(), result.date.toInstant(TimeZone.currentSystemDefault()))
        )
    }
}
