package pl.msiwak.application.usecases

interface AddExerciseUseCase {
    suspend operator fun invoke(categoryId: String, name: String)
}