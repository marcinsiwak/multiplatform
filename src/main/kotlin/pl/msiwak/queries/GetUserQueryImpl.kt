package pl.msiwak.queries

import pl.msiwak.auth.PrincipalProvider
import pl.msiwak.dtos.UserDTO
import pl.msiwak.repositories.UserRepository

class GetUserQueryImpl(
    private val userRepository: UserRepository,
    private val principalProvider: PrincipalProvider
) : GetUserQuery {
    override suspend fun invoke(): UserDTO? {
        // add mapper here
        val userEntity = userRepository.getUser(principalProvider.getPrincipal().userId)
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