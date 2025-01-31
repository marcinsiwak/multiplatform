package pl.msiwak.domain.usecases.notification

interface UnregisterDeviceForNotificationsUseCase {
    suspend operator fun invoke(deviceToken: String)
}
