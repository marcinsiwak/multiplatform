package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.mapper.ExerciseDTOMapper

class GetExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseDTOMapper: ExerciseDTOMapper
) : GetExerciseUseCase {
    override suspend operator fun invoke(id: String): ExerciseDTO? {
        val category = exerciseRepository.getCategoryByExercise(id) ?: return null
        val exercise = category.getExercise(id) ?: return null
        return exerciseDTOMapper(Pair(exercise, category.exerciseType))
    }
}