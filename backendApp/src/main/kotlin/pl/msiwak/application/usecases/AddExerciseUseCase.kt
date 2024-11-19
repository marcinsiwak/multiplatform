package pl.msiwak.application.usecases

import pl.msiwak.multiplatform.shared.model.ApiExercise

interface AddExerciseUseCase {
    suspend operator fun invoke(categoryId: String, name: String): ApiExercise?
}