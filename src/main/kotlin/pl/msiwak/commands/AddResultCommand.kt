package pl.msiwak.commands

import kotlinx.datetime.LocalDateTime

interface AddResultCommand {
    suspend fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    )
}