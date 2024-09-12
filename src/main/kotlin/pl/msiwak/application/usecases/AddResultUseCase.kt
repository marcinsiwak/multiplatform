package pl.msiwak.application.usecases

import kotlinx.datetime.LocalDateTime
import pl.msiwak.interfaces.dtos.ResultDTO

interface AddResultUseCase {
    suspend operator fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    ): ResultDTO?
}