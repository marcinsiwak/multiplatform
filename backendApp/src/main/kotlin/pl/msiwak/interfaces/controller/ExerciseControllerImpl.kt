package pl.msiwak.interfaces.controller

import kotlinx.datetime.Instant
import pl.msiwak.domain.usecases.AddCategoryUseCase
import pl.msiwak.domain.usecases.AddExerciseUseCase
import pl.msiwak.domain.usecases.AddResultUseCase
import pl.msiwak.domain.usecases.GetCategoriesUseCase
import pl.msiwak.domain.usecases.GetCategoryUseCase
import pl.msiwak.domain.usecases.GetExerciseUseCase
import pl.msiwak.domain.usecases.RemoveCategoryUseCase
import pl.msiwak.domain.usecases.RemoveExerciseUseCase
import pl.msiwak.domain.usecases.RemoveResultUseCase
import pl.msiwak.domain.usecases.SynchronizeDataUseCase
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult

@Suppress("LongParameterList")
class ExerciseControllerImpl(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val getExerciseUseCase: GetExerciseUseCase,
    private val removeExerciseUseCase: RemoveExerciseUseCase,
    private val addResultUseCase: AddResultUseCase,
    private val removeResultUseCase: RemoveResultUseCase,
    private val synchronizeDataUseCase: SynchronizeDataUseCase
) : ExerciseController {

    override suspend fun addCategory(name: String, exerciseType: String, userId: String): ApiCategory {
        return addCategoryUseCase(
            name = name,
            exerciseType = exerciseType,
            userId = userId
        )
    }

    override suspend fun getCategories(userId: String): List<ApiCategory> {
        return getCategoriesUseCase(userId = userId)
    }

    override suspend fun getCategory(userId: String): ApiCategory? {
        return getCategoryUseCase(id = userId)
    }

    override suspend fun removeCategory(categoryId: String) {
        removeCategoryUseCase(categoryId = categoryId)
    }

    override suspend fun addExercise(categoryId: String, name: String): ApiExercise? {
        return addExerciseUseCase(
            categoryId = categoryId,
            name = name
        )
    }

    override suspend fun getExercise(exerciseId: String): ApiExercise? {
        return getExerciseUseCase(id = exerciseId)
    }

    override suspend fun removeExercise(exerciseId: String) {
        removeExerciseUseCase(exerciseId = exerciseId)
    }

    override suspend fun addResult(
        exerciseId: String,
        amount: String,
        result: String,
        date: Instant
    ): ApiResult? {
        return addResultUseCase(
            exerciseId = exerciseId,
            amount = amount,
            result = result,
            date = date
        )
    }

    override suspend fun removeResult(resultId: String) {
        removeResultUseCase(resultId)
    }

    override suspend fun synchronizeData(
        categoriesDTO: List<ApiCategory>,
        exercisesDTO: List<ApiExercise>,
        resultsDTO: List<ApiResult>,
        userId: String
    ) {
        synchronizeDataUseCase(categoriesDTO, exercisesDTO, resultsDTO, userId)
    }
}
