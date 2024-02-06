package pl.msiwak.multiplatform.domain.version

interface GetCurrentAppCodeUseCase {
    operator fun invoke(): String
}
