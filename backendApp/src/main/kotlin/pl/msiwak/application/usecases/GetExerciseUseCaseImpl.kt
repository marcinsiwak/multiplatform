package pl.msiwak.application.usecases

import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiExerciseMapper
import pl.msiwak.multiplatform.shared.model.ApiExercise

class GetExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiExerciseMapper: ApiExerciseMapper
) : GetExerciseUseCase {
    override suspend operator fun invoke(id: String): ApiExercise? {
        val category = exerciseRepository.getCategoryByExercise(id) ?: return null
        val exercise = category.getExercise(id) ?: return null
        return apiExerciseMapper(Pair(exercise, category.exerciseType))
    }
}