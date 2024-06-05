package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ChartResultData
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.UnitType
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.utils.DateFormatter

class GetResultsUseCaseImpl(
    private val categoriesRepository: CategoryRepository,
    private val dateFormatter: DateFormatter,
    private val getUnitsUseCase: GetUnitsUseCase,
    private val formatResultsUseCase: FormatResultsUseCase
) : GetResultsUseCase {
    override suspend fun invoke(
        exerciseId: String,
        exerciseType: ExerciseType
    ): List<ChartResultData> {
        val unit = getUnitsUseCase()
        return categoriesRepository.getResults(exerciseId).map {
            val calculatedResult = if (unit == UnitType.IMPERIAL) {
                it.result.toDouble() * exerciseType.convertValue
            } else {
                it.result.toDouble()
            }
            ChartResultData(
                result = calculatedResult,
                amount = it.amount.toDouble(),
                date = dateFormatter.formatDate(it.date)
            )
        }
            .sortedBy { it.date }
    }
}
