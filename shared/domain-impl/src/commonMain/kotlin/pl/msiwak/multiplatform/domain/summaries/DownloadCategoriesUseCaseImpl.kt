package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class DownloadCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) :
    DownloadCategoriesUseCase {
    override suspend operator fun invoke() {
        categoryRepository.downloadCategories()
    }
}
