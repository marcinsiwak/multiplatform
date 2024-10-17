package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.UserRepository
import pl.msiwak.interfaces.dtos.UserDTO
import pl.msiwak.interfaces.mapper.UserDTOMapper

class GetUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val userDTOMapper: UserDTOMapper
) : GetUserUseCase {
    override suspend operator fun invoke(userId: String): UserDTO? {
        val userEntity = userRepository.getUser(userId) ?: return null
        return userDTOMapper(userEntity)
    }
}