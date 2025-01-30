package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class RemoveResultUseCaseImpl(private val categoryRepository: CategoryRepository) :
    RemoveResultUseCase {
    override suspend operator fun invoke(id: String) {
        categoryRepository.removeResult(id)
    }
}
