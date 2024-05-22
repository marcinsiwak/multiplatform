package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise

interface AddExerciseUseCase {
    suspend operator fun invoke(exercise: Exercise): String
}
