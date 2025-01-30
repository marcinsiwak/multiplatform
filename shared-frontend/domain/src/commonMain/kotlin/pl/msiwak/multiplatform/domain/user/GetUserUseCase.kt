package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User

interface GetUserUseCase {
    suspend operator fun invoke(): User
}
