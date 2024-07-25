package pl.msiwak.commands

import pl.msiwak.repositories.UserRepository

class AddUserCommand(private val userRepository: UserRepository) {
    suspend fun invoke(id: String, name: String, email: String) {
        userRepository.addUser(id, name, email)
    }
}
