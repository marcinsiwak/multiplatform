package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.ExerciseEntity
import pl.msiwak.domain.repositories.ExerciseRepository

class AddExerciseUseCaseImpl(private val exerciseRepository: ExerciseRepository) : AddExerciseUseCase {
    override suspend operator fun invoke(categoryId: String, name: String) {
        val category = exerciseRepository.getCategory(categoryId) ?: return
        val exercise = ExerciseEntity(name = name, categoryId = categoryId)
        category.addExercise(exercise)
        exerciseRepository.updateExercises(category)
    }
}