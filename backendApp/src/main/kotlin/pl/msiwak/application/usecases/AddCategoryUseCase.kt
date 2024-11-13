package pl.msiwak.application.usecases

import pl.msiwak.interfaces.dtos.CategoryDTO

interface AddCategoryUseCase {
    suspend operator fun invoke(name: String, exerciseType: String, userId: String): CategoryDTO
}