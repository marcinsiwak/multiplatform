package pl.msiwak.commands

interface RemoveResultCommand {
    suspend fun invoke(resultId: String)
}