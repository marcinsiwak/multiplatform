package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.auth.SessionStore
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.data.local.store.OfflineStore
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult
import pl.msiwak.multiplatform.shared.model.ApiSynchronizationRequest
import pl.msiwak.multiplatform.shared.model.ApiUpdateExerciseNameRequest

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val exercisesDao: ExercisesDao,
    private val resultsDao: ResultsDao,
    private val categoryService: CategoryService,
    private val sessionStore: SessionStore,
    private val offlineStore: OfflineStore
) {

    suspend fun downloadCategories() = withContext(Dispatchers.IO) {
        if (sessionStore.getIsOfflineSession()) return@withContext
        val categories = categoryService.downloadCategories().first()
        categoriesDao.removeAllCategories()
        exercisesDao.removeAllExercises()
        categoriesDao.updateCategories(categories)
        val exercises = categories.map { it.exercises }.flatten()
        exercisesDao.updateExercises(exercises)
    }

    suspend fun downloadCategory(id: String) = withContext(Dispatchers.IO) {
        if (sessionStore.getIsOfflineSession()) return@withContext
        val category = categoryService.downloadCategory(id).first()
        categoriesDao.updateCategory(category)
        exercisesDao.updateExercises(category.exercises)
    }

    suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategory(id)
    }

    suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategories()
    }

    suspend fun createCategory(category: Category) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            categoryService.createCategory(
                ApiCategory(
                    name = category.name,
                    exerciseType = category.exerciseType.name
                )
            )
            return@withContext
        }
        categoriesDao.updateCategory(category.copy(id = offlineStore.getCategoryLastId()))
    }

    suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            categoryService.removeCategory(categoryId)
        }
        categoriesDao.removeCategory(categoryId)
    }

    suspend fun downloadExercise(exerciseId: String) = withContext(Dispatchers.IO) {
        if (sessionStore.getIsOfflineSession()) return@withContext
        val exercise = categoryService.downloadExercise(exerciseId).first()
        exercisesDao.updateExercise(exercise)
        resultsDao.updateResults(exercise.results)
    }

    suspend fun observeExercise(exerciseId: String): Flow<Exercise> = withContext(Dispatchers.IO) {
        return@withContext exercisesDao.observeExercise(exerciseId)
    }

    suspend fun addExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            val exerciseResponse = categoryService.addExercise(
                ApiExercise(
                    categoryId = exercise.categoryId,
                    name = exercise.exerciseTitle,
                    exerciseType = exercise.exerciseType.name
                )
            ).first()
            exercisesDao.updateExercise(exerciseResponse)
            return@withContext exerciseResponse.id
        }
        val lastId = offlineStore.getExerciseLastId()
        exercisesDao.updateExercise(exercise.copy(id = lastId))
        return@withContext lastId
    }

    suspend fun updateExerciseName(exercise: Exercise) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            categoryService.updateExerciseName(
                ApiUpdateExerciseNameRequest(
                    exerciseId = exercise.id,
                    name = exercise.exerciseTitle
                )
            )
            return@withContext
        }
        exercisesDao.updateExercise(exercise)
    }

    suspend fun removeExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            categoryService.removeExercise(exercise.id)
        }
        exercisesDao.removeExercise(exercise)
    }

    suspend fun addResult(result: ResultData) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            val newResult = categoryService.addResult(
                ApiResult(
                    exerciseId = result.exerciseId,
                    result = result.result,
                    amount = result.amount,
                    date = result.date.toInstant(TimeZone.currentSystemDefault())
                )
            ).first()
            resultsDao.updateResult(newResult)
            return@withContext
        }
        resultsDao.updateResult(result.copy(id = offlineStore.getResultLastId()))
    }

    suspend fun removeResult(id: String) = withContext(Dispatchers.IO) {
        if (!sessionStore.getIsOfflineSession()) {
            categoryService.removeResult(id)
        }
        resultsDao.removeResult(id)
    }

    suspend fun clearDatabase() = withContext(Dispatchers.IO) {
        categoriesDao.removeAllCategories()
        exercisesDao.removeAllExercises()
        resultsDao.removeAllResult()
    }

    suspend fun checkIfSynchronizationIsPossible() = withContext(Dispatchers.IO) {
        val categories = categoriesDao.getAllCategories()
        if (categories.isEmpty()) {
            return@withContext false
        }
        return@withContext true
    }

    suspend fun startInitialSynchronization() = withContext(Dispatchers.IO) {
        val categories = categoriesDao.getAllCategories()
        if (categories.isEmpty()) {
            return@withContext
        }
        val exercises = exercisesDao.getAllExercises()
        val results = resultsDao.getAllResults()

        val apiCategories = categories.map { category ->
            ApiCategory(
                id = category.id,
                name = category.name,
                exerciseType = category.exerciseType.name
            )
        }

        val apiExercises = exercises.map { exercise ->
            ApiExercise(
                id = exercise.id,
                categoryId = exercise.categoryId,
                exerciseType = exercise.exerciseType.name,
                name = exercise.exerciseTitle
            )
        }

        val apiResults = results.map { result ->
            ApiResult(
                id = result.id,
                exerciseId = result.exerciseId,
                result = result.result,
                amount = result.amount,
                date = result.date.toInstant(TimeZone.currentSystemDefault())
            )
        }

        categoryService.startInitialSynchronization(
            ApiSynchronizationRequest(
                apiCategories,
                apiExercises,
                apiResults
            )
        )
    }
}
