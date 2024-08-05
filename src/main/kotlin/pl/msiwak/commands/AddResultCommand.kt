package pl.msiwak.commands

import java.time.LocalDateTime

interface AddResultCommand {
    suspend fun invoke(exerciseId: String, amount: String, result: String, dateTime: LocalDateTime)
}