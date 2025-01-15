package pl.msiwak.domain.usecases

interface RemoveCategoryUseCase {
    suspend operator fun invoke(categoryId: String)
}
