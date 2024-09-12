package pl.msiwak.application.usecases

import kotlinx.datetime.LocalDateTime
import pl.msiwak.domain.entities.ResultEntity
import pl.msiwak.domain.repositories.ExerciseRepository
import pl.msiwak.interfaces.dtos.ResultDTO
import pl.msiwak.interfaces.mapper.ResultDTOMapper

class AddResultUseCaseImpl(
    private val exerciseRepository: ExerciseRepository,
    private val resultDTOMapper: ResultDTOMapper
) : AddResultUseCase {
    override suspend operator fun invoke(
        exerciseId: String,
        amount: String,
        result: String,
        date: LocalDateTime
    ): ResultDTO? {
        val category = exerciseRepository.getCategoryByExercise(exerciseId) ?: return null
        val resultEntity = ResultEntity(exerciseId, amount, result, date)
        category.addResult(exerciseId, resultEntity)
        exerciseRepository.updateResults(category)
        return resultDTOMapper(resultEntity)
    }
}