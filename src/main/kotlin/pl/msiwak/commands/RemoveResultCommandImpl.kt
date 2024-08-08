package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository

class RemoveResultCommandImpl(private val exerciseRepository: ExerciseRepository) : RemoveResultCommand {
    override suspend fun invoke(resultId: String) {
        exerciseRepository.removeResult(resultId)
    }
}