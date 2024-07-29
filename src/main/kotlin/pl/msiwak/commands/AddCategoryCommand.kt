package pl.msiwak.commands

interface AddCategoryCommand {
    suspend fun invoke(userId: String, name: String, exerciseType: String)
}