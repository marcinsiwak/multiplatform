package pl.msiwak.application.usecases

interface RemoveExerciseUseCase {
    suspend operator fun invoke(exerciseId: String)
}