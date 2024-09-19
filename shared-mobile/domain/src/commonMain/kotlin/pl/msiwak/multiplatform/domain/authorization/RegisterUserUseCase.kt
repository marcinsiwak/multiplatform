package pl.msiwak.multiplatform.domain.authorization

interface RegisterUserUseCase {
    suspend operator fun invoke(params: Params)

    data class Params(val login: String, val password: String)
}
