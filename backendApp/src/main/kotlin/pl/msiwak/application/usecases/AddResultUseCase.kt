package pl.msiwak.application.usecases

import kotlinx.datetime.Instant
import pl.msiwak.multiplatform.shared.model.ApiResult

interface AddResultUseCase {
    suspend operator fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: Instant
    ): ApiResult?
}