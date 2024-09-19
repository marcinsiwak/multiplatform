package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.ExerciseType

interface AddResultUseCase {
    suspend operator fun invoke(params: Params)

    data class Params(
        val exerciseId: String,
        val result: String,
        val amount: String,
        val date: LocalDateTime,
        val exerciseType: ExerciseType
    )
}
