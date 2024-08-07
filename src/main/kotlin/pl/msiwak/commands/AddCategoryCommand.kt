package pl.msiwak.commands

interface AddCategoryCommand {
    suspend fun invoke(name: String, exerciseType: String)
}