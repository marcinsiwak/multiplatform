package pl.msiwak.domain.usecases

import kotlinx.datetime.Instant
import pl.msiwak.infrastructure.entities.ResultEntity
import pl.msiwak.infrastructure.repositories.ExerciseRepository
import pl.msiwak.interfaces.mapper.ApiResultMapper
import pl.msiwak.multiplatform.shared.model.ApiResult

class AddResultUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val apiResultMapper: ApiResultMapper
) : pl.msiwak.domain.usecases.AddResultUseCase {
    override suspend operator fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: Instant
    ): ApiResult? {
        val category = exerciseRepository.getCategoryByExercise(exerciseId) ?: return null
        val resultEntity = ResultEntity(exerciseId, amount, result, date)
        category.addResult(exerciseId, resultEntity)
        exerciseRepository.updateResults(category)
        return apiResultMapper(resultEntity)
    }
}
