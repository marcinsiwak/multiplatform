package pl.msiwak.commands

import pl.msiwak.repositories.ExerciseRepository

class RemoveCategoryCommandImpl(
    private val exerciseRepository: ExerciseRepository
) : RemoveCategoryCommand {

    override suspend fun invoke(categoryId: String) {
        exerciseRepository.removeCategory(categoryId)
    }
}
