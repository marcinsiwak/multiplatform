package pl.msiwak.multiplatform.core.domain.settings

import pl.msiwak.multiplatform.core.data.store.LanguageStore

class GetLanguageUseCase(private val languageStore: LanguageStore) {
    operator fun invoke(): String {
        return languageStore.geLanguage()
    }
}