package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExerciseDao

class ExerciseRepository(
    private val exerciseDao: ExerciseDao,
    private val categoriesDao: CategoriesDao
) {

    suspend fun clearSummaries() = withContext(Dispatchers.Default) {
        exerciseDao.clearDatabase()
    }

    suspend fun insertExercise(exerciseData: ExerciseData): Long = withContext(Dispatchers.Default) {
        val id = exerciseDao.insertExercise(exerciseData)
        val category = categoriesDao.getCategory(exerciseData.categoryId)
        val newExercisesList = category.exercises.toMutableList()
        newExercisesList.add(ExerciseShort(id = id, name = exerciseData.exerciseTitle))
        categoriesDao.updateCategory(category.copy(exercises = newExercisesList))
        return@withContext id
    }

    suspend fun insertExercises(summaries: List<ExerciseData>) = withContext(Dispatchers.Default) {
        exerciseDao.insertExercises(summaries)
    }

    suspend fun updateExercise(exerciseData: ExerciseData) = withContext(Dispatchers.Default) {
        exerciseDao.updateExercise(exerciseData)
    }

    suspend fun removeExercise(id: Long) = withContext(Dispatchers.Default) {
        exerciseDao.removeExercise(id)
    }

    suspend fun getExercise(id: Long): ExerciseData = withContext(Dispatchers.Default) {
        return@withContext exerciseDao.getExercise(id)
    }

    suspend fun getExercises(): List<ExerciseData> = withContext(Dispatchers.Default) {
        return@withContext exerciseDao.getAllExercises()
    }
}