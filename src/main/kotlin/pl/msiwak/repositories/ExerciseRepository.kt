package pl.msiwak.repositories

import pl.msiwak.database.dao.user.ExercisesDao
import pl.msiwak.entities.CategoryEntity

class ExerciseRepository(private val exercisesDao: ExercisesDao) {

    suspend fun addCategory(
        categoryUuid: String,
        userId: String,
        name: String,
        exerciseType: String
    ) {
        exercisesDao.addCategory(categoryUuid, userId, name, exerciseType)
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
}
