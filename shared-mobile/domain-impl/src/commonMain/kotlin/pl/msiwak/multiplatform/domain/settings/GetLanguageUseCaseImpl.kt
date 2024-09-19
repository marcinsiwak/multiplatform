package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.data.local.store.LanguageStore

class GetLanguageUseCaseImpl(private val languageStore: LanguageStore) : GetLanguageUseCase {
    override operator fun invoke(): String {
        return languageStore.geLanguage()
    }
}
