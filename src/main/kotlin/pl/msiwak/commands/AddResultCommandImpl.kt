package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository
import java.time.LocalDateTime

class AddResultCommandImpl(private val exerciseRepository: ExerciseRepository): AddResultCommand {
    override suspend fun invoke(exerciseId: String, amount: String, result: String, dateTime: LocalDateTime) {
//        exerciseRepository.addResult()
    }
}