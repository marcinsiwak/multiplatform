package pl.msiwak.commands

interface RemoveCategoryCommand {
    suspend fun invoke(categoryId: String)
}