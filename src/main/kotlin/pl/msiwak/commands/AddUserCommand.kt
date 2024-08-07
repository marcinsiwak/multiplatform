package pl.msiwak.commands

interface AddUserCommand {
    suspend fun invoke(name: String, email: String)
}