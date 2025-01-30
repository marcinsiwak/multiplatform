package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class UpdateUserUseCaseImpl(private val userRepository: UserRepository) : UpdateUserUseCase {
    override suspend fun invoke(uuid: String) {
//        userRepository.updateUser(uuid)
    }
}
