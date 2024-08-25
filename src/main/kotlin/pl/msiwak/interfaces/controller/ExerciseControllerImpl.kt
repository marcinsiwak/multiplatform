package pl.msiwak.interfaces.controller

import kotlinx.datetime.LocalDateTime
import pl.msiwak.application.usecases.*
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO

class ExerciseControllerImpl(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val getExerciseUseCase: GetExerciseUseCase,
    private val removeExerciseUseCase: RemoveExerciseUseCase,
    private val addResultUseCase: AddResultUseCase,
    private val removeResultUseCase: RemoveResultUseCase
): ExerciseController {


    override suspend fun addCategory(name: String, exerciseType: String, userId: String) {
        addCategoryUseCase(
            name = name,
            exerciseType = exerciseType,
            userId = userId
        )
    }

    override suspend fun getCategories(userId: String): List<CategoryDTO> {
        return getCategoriesUseCase(userId = userId)
    }

    override suspend fun getCategory(userId: String): CategoryDTO? {
        return getCategoryUseCase(id = userId)
    }

    override suspend fun removeCategory(categoryId: String) {
        removeCategoryUseCase(categoryId = categoryId)
    }

    override suspend fun addExercise(categoryId: String, name: String) {
        addExerciseUseCase(
            categoryId = categoryId,
            name = name
        )
    }

    override suspend fun getExercise(exerciseId: String): ExerciseDTO? {
        return getExerciseUseCase(id = exerciseId)
    }

    override suspend fun removeExercise(exerciseId: String) {
        removeExerciseUseCase(exerciseId = exerciseId)
    }

    override suspend fun addResult(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    ) {
        addResultUseCase(
            exerciseId = exerciseId,
            amount = amount,
            result = result,
            date = date
        )
    }

    override suspend fun removeResult(resultId: String) {
        removeResultUseCase(resultId)
    }
}
