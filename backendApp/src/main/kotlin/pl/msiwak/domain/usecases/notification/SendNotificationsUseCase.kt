package pl.msiwak.domain.usecases.notification

interface SendNotificationsUseCase {
    suspend operator fun invoke(userId: String)
}
