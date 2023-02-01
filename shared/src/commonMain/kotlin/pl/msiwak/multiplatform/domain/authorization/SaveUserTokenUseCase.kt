package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository

class SaveUserTokenUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}