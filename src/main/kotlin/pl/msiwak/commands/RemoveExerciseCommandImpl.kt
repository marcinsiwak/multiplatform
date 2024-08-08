package pl.msiwak.commands

import pl.msiwak.entities.ExerciseEntity
import pl.msiwak.repositories.ExerciseRepository

class RemoveExerciseCommandImpl(private val exerciseRepository: ExerciseRepository) : RemoveExerciseCommand {
    override suspend fun invoke(exerciseId: String) {
        exerciseRepository.removeExercise(exerciseId)
    }
}