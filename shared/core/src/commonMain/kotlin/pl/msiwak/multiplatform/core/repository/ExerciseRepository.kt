package pl.msiwak.multiplatform.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.database.dao.ExerciseDao

class ExerciseRepository(
    private val exerciseDao: ExerciseDao
) {

    suspend fun insertExercise(exercise: Exercise): Long = 0

    suspend fun insertExercises(summaries: List<Exercise>) = withContext(Dispatchers.Default) {
        exerciseDao.insertExercises(summaries)
    }

    suspend fun updateExercise(exercise: Exercise) = withContext(Dispatchers.Default) {
        exerciseDao.updateExercise(exercise)
    }

    suspend fun removeExercise(id: String) = withContext(Dispatchers.Default) {
        exerciseDao.removeExercise(id)
    }

    suspend fun getExercise(id: String): Exercise? = withContext(Dispatchers.Default) {
        return@withContext exerciseDao.getExercise(id)
    }

    suspend fun getExercises(): List<Exercise> = withContext(Dispatchers.Default) {
        return@withContext exerciseDao.getAllExercises()
    }
}