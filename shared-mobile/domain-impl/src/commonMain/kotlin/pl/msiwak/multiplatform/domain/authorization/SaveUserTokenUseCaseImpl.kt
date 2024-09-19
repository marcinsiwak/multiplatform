package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.SessionRepository

class SaveUserTokenUseCaseImpl(private val sessionRepository: SessionRepository) :
    SaveUserTokenUseCase {
    override suspend operator fun invoke(token: String) {
        sessionRepository.saveToken(token)
    }
}
