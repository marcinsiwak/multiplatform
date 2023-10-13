package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class AddResultUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(result: ResultData) {
        categoryRepository.addResult(result)
    }
}
