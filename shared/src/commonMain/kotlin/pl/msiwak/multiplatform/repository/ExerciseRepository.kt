package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.database.dao.ExerciseDao

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {

    suspend fun insertExercise(exerciseData: ExerciseData): Long = withContext(Dispatchers.Default) {
        return@withContext exerciseDao.insertExercise(exerciseData)
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