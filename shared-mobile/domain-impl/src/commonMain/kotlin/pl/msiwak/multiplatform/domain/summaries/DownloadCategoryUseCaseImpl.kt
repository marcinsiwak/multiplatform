package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class DownloadCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    DownloadCategoryUseCase {
    override suspend operator fun invoke(id: String) {
        categoryRepository.downloadCategory(id)
    }
}
