package pl.msiwak.multiplatform.domain.authorization

import pl.msiwak.multiplatform.commonObject.AuthProvider

interface LoginWithProviderUseCase {
    suspend operator fun invoke(authProvider: AuthProvider): String?
}
