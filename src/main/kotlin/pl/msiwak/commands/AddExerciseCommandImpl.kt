package pl.msiwak.commands

import pl.msiwak.entities.ExerciseEntity
import pl.msiwak.repositories.CategoryRepository

class AddExerciseCommandImpl(private val categoryRepository: CategoryRepository) : AddExerciseCommand {
    override suspend fun invoke(categoryId: String, name: String) {
        val category = categoryRepository.getCategory(categoryId) ?: return
        val exercise = ExerciseEntity(name = name)
        category.addExercise(exercise)
        categoryRepository.addExercise(category)
    }
}