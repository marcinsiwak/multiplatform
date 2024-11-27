package pl.msiwak.multiplatform.domain.authorization

interface LoginUseCase {
    suspend operator fun invoke(params: Params): Boolean

    data class Params(val login: String, val password: String)
}
