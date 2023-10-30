package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase

class AddResultUseCase(
    private val categoryRepository: CategoryRepository,
    private val getUnitsUseCase: GetUnitsUseCase
) {
    suspend operator fun invoke(params: Params) {
        val unit = getUnitsUseCase()
        val formattedResult =  if (unit == UnitType.IMPERIAL) {
            params.result.copy(result = (params.result.result.toDouble() / params.exerciseType.convertValue).toString())
        } else {
            params.result
        }

        categoryRepository.addResult(formattedResult)
    }

    data class Params(val result: ResultData, val exerciseType: ExerciseType)
}
