package pl.msiwak.queries

import pl.msiwak.dtos.UserDTO
import pl.msiwak.repositories.UserRepository

class GetUserQueryImpl(
    private val userRepository: UserRepository,
) : GetUserQuery {
    override suspend fun invoke(userId: String): UserDTO? {
        // add mapper here
        val userEntity = userRepository.getUser(userId)
        userEntity?.let {
            return UserDTO(
                id = it.id,
                email = it.email,
                name = it.name
            )
        }
        return null
    }
}