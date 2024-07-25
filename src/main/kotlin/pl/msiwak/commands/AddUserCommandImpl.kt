package pl.msiwak.commands

import pl.msiwak.repositories.UserRepository

class AddUserCommandImpl(private val userRepository: UserRepository): AddUserCommand {
    override suspend fun invoke(id: String, name: String, email: String) {
        userRepository.addUser(id, name, email)
    }
}
