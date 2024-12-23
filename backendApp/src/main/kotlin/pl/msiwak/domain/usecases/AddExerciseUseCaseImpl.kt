package pl.msiwak.domain.usecases

import pl.msiwak.infrastructure.entities.ExerciseEntity
import pl.msiwak.infrastructure.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiExerciseMapper
import pl.msiwak.multiplatform.shared.model.ApiExercise

class AddExerciseUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiExerciseMapper: ApiExerciseMapper
) : pl.msiwak.domain.usecases.AddExerciseUseCase {
    override suspend operator fun invoke(categoryId: String, name: String): ApiExercise? {
        val category = exerciseRepository.getCategory(categoryId) ?: return null
        val exercise = ExerciseEntity(name = name, categoryId = categoryId)
        category.addExercise(exercise)
        exerciseRepository.updateExercises(category)
        return apiExerciseMapper(Pair(exercise, category.exerciseType))
    }
}
