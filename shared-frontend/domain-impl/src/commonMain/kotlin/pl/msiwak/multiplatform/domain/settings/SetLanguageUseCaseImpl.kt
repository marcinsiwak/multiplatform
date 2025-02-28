package pl.msiwak.multiplatform.domain.settings

import pl.msiwak.multiplatform.data.local.store.LanguageStore

class SetLanguageUseCaseImpl(private val store: LanguageStore) : SetLanguageUseCase {
    override operator fun invoke(languageCode: String) {
        store.saveLanguage(languageCode)
//        StringDesc.localeType = StringDesc.LocaleType.Custom(languageCode)
    }
}
