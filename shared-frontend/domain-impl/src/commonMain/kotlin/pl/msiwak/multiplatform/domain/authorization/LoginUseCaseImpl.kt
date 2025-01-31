package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.notifications.NotificationsManager

class LoginUseCaseImpl(
    private val authRepository: AuthRepository,
    private val sessionRepository: SessionRepository,
    private val notificationsManager: NotificationsManager
) : LoginUseCase {
    override suspend fun invoke(params: LoginUseCase.Params): Boolean {
        val result = authRepository.login(params.login, params.password)
        val token = result.user?.token
        val isEmailVerified = result.user?.isEmailVerified ?: false
        if (token != null && isEmailVerified) {
            sessionRepository.saveToken(token)
        }
        runCatching {
            notificationsManager.getToken()?.let {
                sessionRepository.registerDeviceForNotifications(it)
            }
        }
        return isEmailVerified
    }
}
