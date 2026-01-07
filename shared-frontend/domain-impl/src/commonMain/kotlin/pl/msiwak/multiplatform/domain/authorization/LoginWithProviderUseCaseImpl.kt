package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.commonObject.AuthProvider
import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.notifications.NotificationsManager

class LoginWithProviderUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val notificationsManager: NotificationsManager
) : LoginWithProviderUseCase {
    override suspend operator fun invoke(authProvider: AuthProvider): String? {
        val result = authRepository.loginWithProvider(authProvider)
        val token = result.user?.token
        token?.let { sessionRepository.saveToken(it) }
        runCatching {
            notificationsManager.getToken()?.let {
                sessionRepository.registerDeviceForNotifications(it)
            }
        }
        return token
    }
}
