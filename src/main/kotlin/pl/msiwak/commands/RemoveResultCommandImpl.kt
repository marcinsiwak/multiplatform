package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository

class RemoveResultCommandImpl(private val exerciseRepository: ExerciseRepository) : RemoveResultCommand {
    override suspend fun invoke(resultId: String) {
        val category = exerciseRepository.getCategoryByResult(resultId) ?: return
        category.removeResult(resultId)
        exerciseRepository.updateCategory(category)
    }
}