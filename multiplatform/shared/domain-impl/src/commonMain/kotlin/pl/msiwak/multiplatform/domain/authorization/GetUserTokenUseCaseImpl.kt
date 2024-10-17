package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class GetUserTokenUseCaseImpl(private val sessionRepository: SessionRepository) :
    GetUserTokenUseCase {
    override suspend operator fun invoke(): String? {
        return sessionRepository.getToken()
    }
}
