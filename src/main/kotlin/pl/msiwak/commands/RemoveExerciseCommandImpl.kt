package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository

class RemoveExerciseCommandImpl(private val exerciseRepository: ExerciseRepository) : RemoveExerciseCommand {
    override suspend fun invoke(exerciseId: String) {
        val category = exerciseRepository.getCategoryByExercise(exerciseId) ?: return
        category.removeExercise(exerciseId)
        exerciseRepository.updateExercises(category)
    }
}
