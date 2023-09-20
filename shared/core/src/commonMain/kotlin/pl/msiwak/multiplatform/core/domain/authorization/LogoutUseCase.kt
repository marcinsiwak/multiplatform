package pl.msiwak.multiplatform.core.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.logoutUser()
    }
}
