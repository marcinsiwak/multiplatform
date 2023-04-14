package pl.msiwak.multiplatform.ui.main

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinVersionUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class MainViewModel(
    navigator: Navigator,
    getLanguageUseCase: GetLanguageUseCase,
    fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    getMinVersionUseCase: GetMinVersionUseCase
) : ViewModel() {

    val mainNavigator = navigator

    init {
        viewModelScope.launch {
            fetchRemoteConfigUseCase()
            val min = getMinVersionUseCase()
        }
        val language = getLanguageUseCase()
        StringDesc.localeType = StringDesc.LocaleType.Custom(language)
    }
}