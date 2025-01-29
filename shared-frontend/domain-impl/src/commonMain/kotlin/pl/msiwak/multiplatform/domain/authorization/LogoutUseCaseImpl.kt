package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class LogoutUseCaseImpl(
    private val authRepository: AuthRepository,
    private val categoryRepository: CategoryRepository
) : LogoutUseCase {
    override suspend operator fun invoke() {
        authRepository.logoutUser()
        categoryRepository.clearDatabase()
    }
}
