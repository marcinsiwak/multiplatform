package pl.msiwak.commands

import pl.msiwak.auth.PrincipalProvider
import pl.msiwak.entities.CategoryEntity
import pl.msiwak.repositories.ExerciseRepository

class AddCategoryCommandImpl(
    private val exerciseRepository: ExerciseRepository,
    private val principalProvider: PrincipalProvider
) : AddCategoryCommand {
    override suspend fun invoke(name: String, exerciseType: String) {
        val category = CategoryEntity(
            userId = principalProvider.getPrincipal().userId,
            name = name,
            exerciseType = exerciseType
        )
        exerciseRepository.updateCategory(category)
    }
}
