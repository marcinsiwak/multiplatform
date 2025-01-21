package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
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

class CategoryRepositoryImpl(
    private val categoriesDao: CategoriesDao,
    private val exercisesDao: ExercisesDao,
    private val resultsDao: ResultsDao,
    private val categoryService: CategoryService,
    private val offlineStore: OfflineStore
) : CategoryRepository {

    override suspend fun downloadCategories() = withContext(Dispatchers.IO) {
        val categories = categoryService.downloadCategories().first()
        categoriesDao.removeAllCategories()
        exercisesDao.removeAllExercises()
        categoriesDao.updateCategories(categories)
        val exercises = categories.map { it.exercises }.flatten()
        exercisesDao.updateExercises(exercises)
    }

    override suspend fun downloadCategory(id: String) = withContext(Dispatchers.IO) {
        val category = categoryService.downloadCategory(id).first()
        categoriesDao.updateCategory(category)
        exercisesDao.updateExercises(category.exercises)
    }

    override suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategory(id)
    }

    override suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext categoriesDao.observeCategories()
    }

    override suspend fun createCategory(name: String, exerciseType: ExerciseType) = withContext(Dispatchers.IO) {
        categoryService.createCategory(
            name = name,
            exerciseType = exerciseType
        )
    }

    override suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.IO) {
        categoryService.removeCategory(categoryId)
        categoriesDao.removeCategory(categoryId)
    }

    override suspend fun downloadExercise(exerciseId: String) = withContext(Dispatchers.IO) {
        val exercise = categoryService.downloadExercise(exerciseId).first()
        exercisesDao.updateExercise(exercise)
        resultsDao.updateResults(exercise.results)
    }

    override suspend fun observeExercise(exerciseId: String): Flow<Exercise> = withContext(Dispatchers.IO) {
        return@withContext exercisesDao.observeExercise(exerciseId)
    }

    override suspend fun addExercise(categoryId: String, name: String, exerciseType: ExerciseType): String =
        withContext(Dispatchers.IO) {
            val exerciseResponse = categoryService.addExercise(categoryId, name, exerciseType).first()
            exercisesDao.updateExercise(exerciseResponse)
            return@withContext exerciseResponse.id
        }

    override suspend fun updateExerciseName(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.updateExerciseName(
            ApiUpdateExerciseNameRequest(
                exerciseId = exercise.id,
                name = exercise.exerciseTitle
            )
        )
    }

    override suspend fun removeExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.removeExercise(exercise.id)
        exercisesDao.removeExercise(exercise)
    }

    override suspend fun addResult(exerciseId: String, result: String, amount: String, dateTime: LocalDateTime) =
        withContext(Dispatchers.IO) {
            val newResult = categoryService.addResult(exerciseId, result, amount, dateTime).first()
            resultsDao.updateResult(newResult)
        }

    override suspend fun removeResult(id: String) = withContext(Dispatchers.IO) {
        categoryService.removeResult(id)
        resultsDao.removeResult(id)
    }

    override suspend fun clearDatabase() = withContext(Dispatchers.IO) {
        categoriesDao.removeAllCategories()
        exercisesDao.removeAllExercises()
        resultsDao.removeAllResult()
    }

    override suspend fun checkIfSynchronizationIsPossible() = withContext(Dispatchers.IO) {
        val categories = categoriesDao.getAllCategories()
        return@withContext categories.isNotEmpty()
    }

    override suspend fun startInitialSynchronization() = withContext(Dispatchers.IO) {
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
