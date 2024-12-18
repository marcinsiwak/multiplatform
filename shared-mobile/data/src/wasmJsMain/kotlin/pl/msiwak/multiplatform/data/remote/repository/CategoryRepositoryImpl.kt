package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun downloadCategories() {}

    override suspend fun downloadCategory(id: String) {}

    override suspend fun observeCategory(id: String): Flow<Category> {
        return flow {
            Category()
        }
    }

    override suspend fun observeCategories(): Flow<List<Category>> {
        return flow { listOf(Category()) }
    }

    override suspend fun createCategory(category: Category) {}

    override suspend fun removeCategory(categoryId: String) {}

    override suspend fun downloadExercise(exerciseId: String) {}

    override suspend fun observeExercise(exerciseId: String): Flow<Exercise> {
        return flow {
            Exercise()
        }
    }

    override suspend fun addExercise(exercise: Exercise): String {
        return ""
    }

    override suspend fun updateExerciseName(exercise: Exercise) {}

    override suspend fun removeExercise(exercise: Exercise) {}

    override suspend fun addResult(result: ResultData) {}

    override suspend fun removeResult(id: String) {}

    override suspend fun clearDatabase() {}

    override suspend fun checkIfSynchronizationIsPossible(): Boolean {
        return true
    }

    override suspend fun startInitialSynchronization() {}
}
