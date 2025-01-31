package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.notifications.NotificationsManager

class GoogleLoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val notificationsManager: NotificationsManager
) : GoogleLoginUseCase {
    override suspend operator fun invoke(tokenId: String?, accessToken: String?): String? {
        val result = authRepository.loginWithGoogle(tokenId, accessToken)
        val token = result?.user?.token
        token?.let { sessionRepository.saveToken(it) }
        runCatching {
            notificationsManager.getToken()?.let {
                sessionRepository.registerDeviceForNotifications(it)
            }
        }
        return token
    }
}
