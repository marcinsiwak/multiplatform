package pl.msiwak.multiplatform.ui.main

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.api.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.GetMinAppCodeUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.version.GetCurrentAppCodeUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class MainViewModel(
    navigator: Navigator,
    getLanguageUseCase: GetLanguageUseCase,
    fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    getMinAppCodeUseCase: GetMinAppCodeUseCase,
    getCurrentAppCodeUseCase: GetCurrentAppCodeUseCase,
    globalErrorHandler: GlobalErrorHandler,
    getUserTokenUseCase: GetUserTokenUseCase,
    observeAuthStateChangedUseCase: ObserveAuthStateChangedUseCase
) : ViewModel() {

    val mainNavigator = navigator

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            observeAuthStateChangedUseCase()
            fetchRemoteConfigUseCase()
            val language = getLanguageUseCase()
            StringDesc.localeType = StringDesc.LocaleType.Custom(language)
            val currentVersion = getCurrentAppCodeUseCase().toLong()
            val min = getMinAppCodeUseCase().toLong()
            if (currentVersion < min) {
                navigator.navigate(NavigationDirections.ForceUpdate)
            }
            if (!getUserTokenUseCase().isNullOrEmpty()){
                navigator.navigate(NavigationDirections.Dashboard)
            }
        }
    }
}