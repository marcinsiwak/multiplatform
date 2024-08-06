package pl.msiwak.commands

interface AddExerciseCommand {
    suspend fun invoke(categoryId: String, name: String, userId: String)
}