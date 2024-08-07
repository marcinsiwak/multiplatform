package pl.msiwak.repositories

import pl.msiwak.database.dao.user.ExercisesDao
import pl.msiwak.entities.CategoryEntity

class ExerciseRepository(private val exercisesDao: ExercisesDao) {

    suspend fun addCategory(categoryEntity: CategoryEntity) {
        exercisesDao.addCategory(categoryEntity)
    }

    suspend fun getCategory(categoryId: String): CategoryEntity? {
        return exercisesDao.getCategory(categoryId)
    }

    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity? {
        return exercisesDao.getCategoryByExercise(exerciseId)
    }

    suspend fun getCategories(): List<CategoryEntity> {
        return exercisesDao.getCategories()
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
