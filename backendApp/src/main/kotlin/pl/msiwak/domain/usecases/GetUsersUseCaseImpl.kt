package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.UserRepository
import pl.msiwak.interfaces.mapper.ApiUserMapper
import pl.msiwak.multiplatform.shared.model.ApiUser

class GetUsersUseCaseImpl(
    private val userRepository: UserRepository,
    private val apiUserMapper: ApiUserMapper
) : GetUsersUseCase {
    override suspend fun invoke(): List<ApiUser> {
        return userRepository.getUsers().map { apiUserMapper(it) }
    }
}
