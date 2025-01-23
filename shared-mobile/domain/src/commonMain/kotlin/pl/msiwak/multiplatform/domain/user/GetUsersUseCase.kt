package pl.msiwak.multiplatform.domain.user

import pl.msiwak.multiplatform.commonObject.User

interface GetUsersUseCase {
    suspend operator fun invoke(): List<User>
}
