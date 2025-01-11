package pl.msiwak.domain.usecases

interface RemoveExerciseUseCase {
    suspend operator fun invoke(exerciseId: String)
}
