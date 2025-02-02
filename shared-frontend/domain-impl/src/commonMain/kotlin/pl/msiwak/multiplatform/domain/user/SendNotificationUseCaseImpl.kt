package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User
import pl.msiwak.multiplatform.data.remote.repository.UserRepository

class SendNotificationUseCaseImpl(private val userRepository: UserRepository) : SendNotificationUseCase {
    override suspend fun invoke(user: User) {
        userRepository.sendNotifications(user)
    }
}
