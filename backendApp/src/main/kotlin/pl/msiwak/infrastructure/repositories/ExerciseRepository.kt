package pl.msiwak.infrastructure.repositories

import pl.msiwak.infrastructure.database.dao.exercise.ExercisesDao
import pl.msiwak.infrastructure.entities.CategoryEntity

class ExerciseRepository(private val exercisesDao: ExercisesDao) {

    suspend fun getCategory(categoryId: String): CategoryEntity? {
        return exercisesDao.getCategory(categoryId)
    }

    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity? {
        return exercisesDao.getCategoryByExercise(exerciseId)
    }

    suspend fun getCategoryByResult(resultId: String): CategoryEntity? {
        return exercisesDao.getCategoryByResult(resultId)
    }

    suspend fun getCategories(userId: String): List<CategoryEntity> {
        return exercisesDao.getCategories(userId)
    }

    suspend fun removeCategory(categoryId: String) {
        exercisesDao.removeCategory(categoryId)
    }

    suspend fun updateCategory(category: CategoryEntity?) {
        category?.let { exercisesDao.updateCategory(it) } ?: return
    }

    suspend fun updateExercises(category: CategoryEntity?) {
        category?.let { exercisesDao.updateExercises(it) } ?: return
    }

    suspend fun updateResults(category: CategoryEntity?) {
        category?.let { exercisesDao.updateResults(it) } ?: return
    }

    suspend fun synchronizeData(
        categories: List<CategoryEntity>
    ) {
        with(exercisesDao) {
            syncCategories(categories)
            val exercises = categories.map { it.exercises }.flatten().also {
                syncExercises(it)
            }
            exercises.map { it.results }.flatten().let {
                syncResults(it)
            }
        }
    }

    suspend fun clearUserData(userId: String) {
        exercisesDao.clearUserData(userId)
    }
}
