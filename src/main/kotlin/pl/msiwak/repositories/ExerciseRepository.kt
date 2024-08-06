package pl.msiwak.repositories

import pl.msiwak.database.dao.user.ExercisesDao
import pl.msiwak.entities.CategoryEntity

class ExerciseRepository(private val exercisesDao: ExercisesDao) {

    suspend fun addCategory(categoryEntity: CategoryEntity) {
        exercisesDao.addCategory(categoryEntity)
    }

    suspend fun getCategory(categoryId: String, userId: String): CategoryEntity? {
        return exercisesDao.getCategory(categoryId, userId)
    }

    suspend fun getCategoryByExercise(exerciseId: String, userId: String): CategoryEntity? {
        return exercisesDao.getCategoryByExercise(exerciseId, userId)
    }

    suspend fun getCategories(userId: String): List<CategoryEntity> {
        return exercisesDao.getCategories(userId)
    }

    suspend fun removeCategory(categoryId: String) {
        exercisesDao.removeCategory(categoryId)
    }

    suspend fun addExercise(categoryEntity: CategoryEntity) {
        exercisesDao.addExercise(categoryEntity)
    }

    suspend fun addResult(categoryEntity: CategoryEntity) {
        exercisesDao.addResult(categoryEntity)
    }
}
