package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository
import java.util.*

class AddCategoryCommandImpl(private val exerciseRepository: ExerciseRepository): AddCategoryCommand {
    override suspend fun invoke(userId: String, name: String, exerciseType: String) {
        val categoryUuid = UUID.randomUUID().toString()
        exerciseRepository.addCategory(categoryUuid, userId, name, exerciseType)
    }
}
