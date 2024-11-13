package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO

interface GetCategoryUseCase {
    suspend operator fun invoke(id: String): CategoryDTO?
}