package pl.msiwak.commands

import pl.msiwak.auth.PrincipalProvider
import pl.msiwak.repositories.UserRepository

class AddUserCommandImpl(
    private val userRepository: UserRepository,
    private val principalProvider: PrincipalProvider
) : AddUserCommand {
    override suspend fun invoke(name: String, email: String) {
        userRepository.addUser(principalProvider.getPrincipal().userId, name, email)
    }
}
