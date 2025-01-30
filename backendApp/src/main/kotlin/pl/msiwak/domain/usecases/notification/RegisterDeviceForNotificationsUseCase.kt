package pl.msiwak.domain.usecases.notification

interface RegisterDeviceForNotificationsUseCase {
    suspend operator fun invoke(deviceToken: String, userId: String)
}
