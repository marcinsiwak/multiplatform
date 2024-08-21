package pl.msiwak.commands

import pl.msiwak.entities.CategoryEntity
import pl.msiwak.repositories.ExerciseRepository

class AddCategoryCommandImpl(
    private val exerciseRepository: ExerciseRepository,
) : AddCategoryCommand {
    override suspend fun invoke(name: String, exerciseType: String, userId: String) {
        val category = CategoryEntity(
            userId = userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
    }
}
