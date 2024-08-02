package pl.msiwak.commands

import pl.msiwak.entities.ExerciseEntity
import pl.msiwak.repositories.ExerciseRepository

class AddExerciseCommandImpl(private val exerciseRepository: ExerciseRepository) : AddExerciseCommand {
    override suspend fun invoke(categoryId: String, name: String) {
        val category = exerciseRepository.getCategory(categoryId) ?: return
        val exercise = ExerciseEntity(name = name)
        category.addExercise(exercise)
        exerciseRepository.addExercise(category)
    }
}