package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class DownloadCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepository.downloadCategories()
    }
}
