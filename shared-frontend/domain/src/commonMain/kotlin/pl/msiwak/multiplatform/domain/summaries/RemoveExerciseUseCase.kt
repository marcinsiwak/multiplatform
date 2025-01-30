package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Exercise

interface RemoveExerciseUseCase {
    suspend operator fun invoke(exercise: Exercise)
}
