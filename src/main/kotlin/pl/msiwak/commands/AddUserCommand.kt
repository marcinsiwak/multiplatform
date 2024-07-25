package pl.msiwak.commands

interface AddUserCommand {
    suspend fun invoke(id: String, name: String, email: String)
}