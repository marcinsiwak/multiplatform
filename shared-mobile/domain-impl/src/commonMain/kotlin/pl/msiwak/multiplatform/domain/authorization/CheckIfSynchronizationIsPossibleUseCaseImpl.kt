package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class CheckIfSynchronizationIsPossibleUseCaseImpl(private val categoryRepository: CategoryRepository) :
    CheckIfSynchronizationIsPossibleUseCase {
    override suspend operator fun invoke() = categoryRepository.checkIfSynchronizationIsPossible()
}
