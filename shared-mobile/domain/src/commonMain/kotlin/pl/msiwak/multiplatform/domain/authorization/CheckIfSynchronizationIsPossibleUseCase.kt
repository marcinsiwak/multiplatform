package pl.msiwak.multiplatform.domain.authorization

interface CheckIfSynchronizationIsPossibleUseCase {
    suspend operator fun invoke(): Boolean
}
