package pl.msiwak.domain.usecases

import pl.msiwak.multiplatform.shared.model.ApiCategory

interface GetCategoryUseCase {
    suspend operator fun invoke(id: String): ApiCategory?
}
