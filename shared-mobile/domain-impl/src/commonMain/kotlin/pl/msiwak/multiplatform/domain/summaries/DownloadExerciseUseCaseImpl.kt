package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class DownloadExerciseUseCaseImpl(private val categoryRepository: CategoryRepository) :
    DownloadExerciseUseCase {
    override suspend operator fun invoke(id: String) {
        categoryRepository.downloadExercise(id)
    }
}
