package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class DeleteAccountUseCaseImpl(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
) : DeleteAccountUseCase {
    override suspend operator fun invoke() {
        userRepository.deleteUser()
        categoryRepository.clearDatabase()
    }
}
