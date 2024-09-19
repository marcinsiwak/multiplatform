package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class SynchronizeDatabaseUseCaseImpl(private val categoryRepository: CategoryRepository) :
    SynchronizeDatabaseUseCase {
    override suspend operator fun invoke() {
        categoryRepository.startInitialSynchronization()
    }
}
