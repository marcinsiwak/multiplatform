package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData

class CategoryRepositoryImpl() : CategoryRepository {
    override suspend fun downloadCategories() {
        TODO("Not yet implemented")
    }

    override suspend fun downloadCategory(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun observeCategory(id: String): Flow<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun observeCategories(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override suspend fun removeCategory(categoryId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun downloadExercise(exerciseId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun observeExercise(exerciseId: String): Flow<Exercise> {
        TODO("Not yet implemented")
    }

    override suspend fun addExercise(exercise: Exercise): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateExerciseName(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun removeExercise(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun addResult(result: ResultData) {
        TODO("Not yet implemented")
    }

    override suspend fun removeResult(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun checkIfSynchronizationIsPossible(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun startInitialSynchronization() {
        TODO("Not yet implemented")
    }


}