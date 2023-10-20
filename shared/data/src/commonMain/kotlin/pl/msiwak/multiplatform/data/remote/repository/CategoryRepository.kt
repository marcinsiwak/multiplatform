package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResultRequest
import pl.msiwak.multiplatform.network.model.ApiUpdateExerciseNameRequest
import pl.msiwak.multiplatform.network.service.CategoryService

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val exercisesDao: ExercisesDao,
    private val resultsDao: ResultsDao,
    private val categoryService: CategoryService
) {

    suspend fun downloadCategories(): List<Category> = withContext(Dispatchers.IO) {
        val categories = categoryService.downloadCategories()
        categoriesDao.removeAllCategories()
        exercisesDao.removeAllExercises()
        categoriesDao.updateCategories(categories)
        val exercises = categories.map { it.exercises }.flatten()
        exercisesDao.updateExercises(exercises)
        return@withContext categories
    }

    suspend fun downloadCategory(id: String): Category = withContext(Dispatchers.IO) {
        val category = categoryService.downloadCategory(id)
        categoriesDao.updateCategory(category)
        exercisesDao.updateExercises(category.exercises)
        return@withContext category
    }

    suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategory(id)
    }

    suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategories()
    }

    suspend fun insertCategories(categories: List<Category>) =
        withContext(Dispatchers.IO) {
            categoriesDao.insertCategories(categories)
        }

    suspend fun createCategory(category: Category) = withContext(Dispatchers.IO) {
        categoryService.createCategory(
            ApiCategoryRequest(
                name = category.name,
                exerciseType = category.exerciseType.name,
            )
        )
    }

    suspend fun updateCategory(category: Category) = withContext(Dispatchers.IO) {
        categoriesDao.updateCategory(category)
    }

    suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.IO) {
        categoryService.removeCategory(categoryId)
        categoriesDao.removeCategory(categoryId)
    }

    suspend fun downloadExercise(exerciseId: String) = withContext(Dispatchers.IO) {
        val exercise = categoryService.downloadExercise(exerciseId)
        exercisesDao.updateExercise(exercise)
        resultsDao.updateResults(exercise.results)
    }

    suspend fun observeExercise(exerciseId: String): Flow<Exercise> =
        withContext(Dispatchers.IO) {
            return@withContext exercisesDao.observeExercise(exerciseId)
        }

    suspend fun addExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        val exerciseResponse = categoryService.addExercise(
            ApiExerciseRequest(
                categoryId = exercise.categoryId,
                name = exercise.exerciseTitle
            )
        )
        exercisesDao.updateExercise(exerciseResponse)
        return@withContext exerciseResponse.id
    }

    suspend fun updateExerciseName(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.updateExerciseName(ApiUpdateExerciseNameRequest(exercise.id, exercise.exerciseTitle))
    }

    suspend fun removeExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.removeExercise(exercise.id)
        exercisesDao.removeExercise(exercise)
    }

    suspend fun addResult(result: ResultData) = withContext(Dispatchers.IO) {
        val newResult = categoryService.addResult(
            ApiResultRequest(
                result.exerciseId,
                result.result,
                result.amount.toDouble(),
                result.date.toInstant(TimeZone.currentSystemDefault())
            )
        )
        resultsDao.updateResult(newResult)
    }

    suspend fun removeResult(id: String) = withContext(Dispatchers.IO) {
        categoryService.removeResult(id)
        resultsDao.removeResult(id)
    }
}
