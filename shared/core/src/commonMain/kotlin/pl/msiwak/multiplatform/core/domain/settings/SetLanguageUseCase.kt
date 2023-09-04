package pl.msiwak.multiplatform.core.domain.settings

import dev.icerock.moko.resources.desc.StringDesc
import pl.msiwak.multiplatform.core.data.store.LanguageStore

class SetLanguageUseCase(private val store: LanguageStore) {
    operator fun invoke(languageCode: String) {
        store.saveLanguage(languageCode)
        StringDesc.localeType = StringDesc.LocaleType.Custom(languageCode)
    }
}