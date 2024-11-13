package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO

interface GetCategoriesUseCase {
    suspend operator fun invoke(userId: String): List<CategoryDTO>
}