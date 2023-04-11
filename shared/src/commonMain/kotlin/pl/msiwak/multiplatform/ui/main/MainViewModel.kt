package pl.msiwak.multiplatform.ui.main

import dev.icerock.moko.resources.desc.StringDesc
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class MainViewModel(navigator: Navigator, getLanguageUseCase: GetLanguageUseCase) : ViewModel() {

    val mainNavigator = navigator

    init {
        val language = getLanguageUseCase()
        StringDesc.localeType = StringDesc.LocaleType.Custom(language)
    }
}