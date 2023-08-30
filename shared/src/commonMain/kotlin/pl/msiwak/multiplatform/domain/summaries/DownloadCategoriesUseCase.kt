package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.repository.CategoryRepository

class DownloadCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepository.downloadCategories()
    }
}
