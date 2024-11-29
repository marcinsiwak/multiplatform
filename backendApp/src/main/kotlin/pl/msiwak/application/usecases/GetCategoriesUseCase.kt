package pl.msiwak.application.usecases

import pl.msiwak.multiplatform.shared.model.ApiCategory

interface GetCategoriesUseCase {
    suspend operator fun invoke(userId: String): List<ApiCategory>
}
