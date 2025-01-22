package pl.msiwak.domain.usecases

import pl.msiwak.multiplatform.shared.model.ApiUser

interface GetUsersUseCase {
    suspend operator fun invoke(): List<ApiUser>
}
