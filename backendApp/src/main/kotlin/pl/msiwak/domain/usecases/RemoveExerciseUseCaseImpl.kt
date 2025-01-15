package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.repositories.ExerciseRepository

class RemoveExerciseUseCaseImpl(private val exerciseRepository: ExerciseRepository) : RemoveExerciseUseCase {
    override suspend operator fun invoke(exerciseId: String) {
        val category = exerciseRepository.getCategoryByExercise(exerciseId) ?: return
        category.removeExercise(exerciseId)
        exerciseRepository.updateExercises(category)
    }
}
