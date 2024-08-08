package pl.msiwak.commands

interface RemoveExerciseCommand {
    suspend fun invoke(exerciseId: String)
}