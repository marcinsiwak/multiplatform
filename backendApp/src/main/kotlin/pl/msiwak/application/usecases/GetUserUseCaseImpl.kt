package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.UserRepository
import pl.msiwak.interfaces.mapper.ApiUserMapper
import pl.msiwak.multiplatform.shared.model.ApiUser

class GetUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val apiUserMapper: ApiUserMapper
) : GetUserUseCase {
    override suspend operator fun invoke(userId: String): ApiUser? {
        val userEntity = userRepository.getUser(userId) ?: return null
        return apiUserMapper(userEntity)
    }
}