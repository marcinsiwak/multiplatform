package pl.msiwak.application.usecases

import pl.msiwak.multiplatform.shared.model.ApiExercise

interface GetExerciseUseCase {
    suspend operator fun invoke(id: String): ApiExercise?
}
