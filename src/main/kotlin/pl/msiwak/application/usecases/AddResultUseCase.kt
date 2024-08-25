package pl.msiwak.application.usecases

import kotlinx.datetime.LocalDateTime

interface AddResultUseCase {
    suspend operator fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    )
}