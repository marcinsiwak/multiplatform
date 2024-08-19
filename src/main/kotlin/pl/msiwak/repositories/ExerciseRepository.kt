package pl.msiwak.repositories

import pl.msiwak.database.dao.exercise.ExercisesDao
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

    suspend fun getCategoryByResult(resultId: String): CategoryEntity? {
        return exercisesDao.getCategoryByResult(resultId)
    }

    suspend fun getCategories(): List<CategoryEntity> {
        return exercisesDao.getCategories()
    }

    suspend fun removeCategory(categoryId: String) {
        exercisesDao.removeCategory(categoryId)
    }

    suspend fun removeExercise(exerciseId: String) {
        exercisesDao.removeExercise(exerciseId)
    }

    suspend fun removeResult(resultId: String) {
        exercisesDao.removeResult(resultId)
    }

    suspend fun addExercise(categoryEntity: CategoryEntity) {
        exercisesDao.addExercise(categoryEntity)
    }

    suspend fun addResult(categoryEntity: CategoryEntity) {
        exercisesDao.addResult(categoryEntity)
    }

    suspend fun updateCategory(category: CategoryEntity?) {
        category?.let { exercisesDao.updateCategory(it) } ?: return
    }
}
