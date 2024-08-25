package pl.msiwak.application.usecases

interface AddCategoryUseCase {
    suspend operator fun invoke(name: String, exerciseType: String, userId: String)
}