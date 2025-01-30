package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType

interface AddExerciseUseCase {
    suspend operator fun invoke(params: Params): String

    data class Params(val categoryId: String, val name: String, val exerciseType: ExerciseType)
}
