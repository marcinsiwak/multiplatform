package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User

interface SendNotificationUseCase {
    suspend operator fun invoke(user: User)
}
