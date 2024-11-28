package pl.msiwak.application.usecases

import pl.msiwak.multiplatform.shared.model.ApiUser

interface GetUserUseCase {
    suspend operator fun invoke(userId: String): ApiUser?
}
