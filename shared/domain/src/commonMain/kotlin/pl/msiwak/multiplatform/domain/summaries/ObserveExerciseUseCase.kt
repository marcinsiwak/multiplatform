package pl.msiwak.multiplatform.domain.summaries

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Exercise

interface ObserveExerciseUseCase {
    suspend operator fun invoke(id: String): Flow<Exercise>
}
