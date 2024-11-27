package pl.msiwak.multiplatform.domain.settings

interface SetLanguageUseCase {
    operator fun invoke(languageCode: String)
}
