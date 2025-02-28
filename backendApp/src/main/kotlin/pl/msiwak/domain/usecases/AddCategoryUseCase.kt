package pl.msiwak.domain.usecases

import pl.msiwak.multiplatform.shared.model.ApiCategory

interface AddCategoryUseCase {
    suspend operator fun invoke(name: String, exerciseType: String, userId: String): ApiCategory
}
