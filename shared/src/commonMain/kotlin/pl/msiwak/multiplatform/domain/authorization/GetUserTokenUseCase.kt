package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.repository.AuthRepository

class GetUserTokenUseCase(private val authRepository: AuthRepository) {
    operator fun invoke(): String? {
        return authRepository.getToken()
    }
}