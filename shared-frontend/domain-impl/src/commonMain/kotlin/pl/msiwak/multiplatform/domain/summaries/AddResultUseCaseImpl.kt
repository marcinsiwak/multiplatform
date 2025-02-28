package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase

class AddResultUseCaseImpl(
    private val categoryRepository: CategoryRepository,
    private val getUnitsUseCase: GetUnitsUseCase
) : AddResultUseCase {
    override suspend fun invoke(params: AddResultUseCase.Params) {
        val unit = getUnitsUseCase()
        with(params) {
            categoryRepository.addResult(
                exerciseId = exerciseId,
                result = if (unit == UnitType.IMPERIAL) (result.toDouble() / params.exerciseType.convertValue).toString() else result,
                amount = amount,
                dateTime = date
            )
        }
    }
}
