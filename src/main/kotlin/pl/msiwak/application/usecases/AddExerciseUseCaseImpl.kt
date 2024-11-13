package pl.msiwak.application.usecases

import pl.msiwak.domain.entities.ExerciseEntity
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.mapper.ExerciseDTOMapper

class AddExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseDTOMapper: ExerciseDTOMapper
) : AddExerciseUseCase {
    override suspend operator fun invoke(categoryId: String, name: String): ExerciseDTO? {
        val category = exerciseRepository.getCategory(categoryId) ?: return null
        val exercise = ExerciseEntity(name = name, categoryId = categoryId)
        category.addExercise(exercise)
        exerciseRepository.updateExercises(category)
        return exerciseDTOMapper(Pair(exercise, category.exerciseType))
    }
}