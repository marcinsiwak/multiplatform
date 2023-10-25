package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository

class CheckIfSynchronizationIsPossibleUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke() = categoryRepository.checkIfSynchronizationIsPossible()

}