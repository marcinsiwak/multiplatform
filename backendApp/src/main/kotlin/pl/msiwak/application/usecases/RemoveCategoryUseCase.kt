package pl.msiwak.application.usecases

interface RemoveCategoryUseCase {
    suspend operator fun invoke(categoryId: String)
}