package pl.msiwak.repositories

import pl.msiwak.database.dao.user.ExercisesDao
import pl.msiwak.entities.CategoryEntity

class ExerciseRepository(private val exercisesDao: ExercisesDao) {

    suspend fun addCategory(
        categoryEntity: CategoryEntity
    ) {
        exercisesDao.addCategory(categoryEntity)
    }

    suspend fun getCategory(categoryId: String): CategoryEntity? {
        return exercisesDao.getCategory(categoryId)
    }

    suspend fun getCategories(userId: String): List<CategoryEntity> {
        return exercisesDao.getCategories(userId)
    }

    suspend fun removeCategory(categoryId: String) {
        exercisesDao.removeCategory(categoryId)
    }

    suspend fun addExercise(
        categoryEntity: CategoryEntity
    ) {
        exercisesDao.addExercise(categoryEntity)
    }
}
