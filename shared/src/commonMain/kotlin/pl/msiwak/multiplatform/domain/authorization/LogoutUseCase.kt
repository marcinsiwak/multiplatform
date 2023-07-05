package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
//        authRepository.logoutUser()
    }
}
