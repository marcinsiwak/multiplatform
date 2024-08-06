package pl.msiwak.commands

import pl.msiwak.entities.ResultEntity

interface AddResultCommand {
    suspend fun invoke(exerciseId: String, resultEntity: ResultEntity, userId: String)
}