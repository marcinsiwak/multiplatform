package pl.msiwak.multiplatform.core.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.domain.offline.GetIsOfflineModeUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.settings.GetLanguageUseCase
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class MainViewModel(
    navigator: Navigator,
    getLanguageUseCase: GetLanguageUseCase,
    fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    getForceUpdateStateUseCase: GetForceUpdateStateUseCase,
    globalErrorHandler: GlobalErrorHandler,
    getUserTokenUseCase: GetUserTokenUseCase,
    observeAuthStateChangedUseCase: ObserveAuthStateChangedUseCase,
    getIsOfflineModeUseCase: GetIsOfflineModeUseCase
) : ViewModel() {

    val mainNavigator = navigator

    private val errorHandler = globalErrorHandler.handleError()

    private val _viewState = MutableStateFlow(MainState())
    val viewState: StateFlow<MainState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch(errorHandler) {
            observeAuthStateChangedUseCase()
        }
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            fetchRemoteConfigUseCase()
            StringDesc.localeType = StringDesc.LocaleType.Custom(getLanguageUseCase())

            if (!getUserTokenUseCase().isNullOrEmpty()) {
                _viewState.update { it.copy(directions = NavigationDirections.Dashboard(true)) }
            }
            if (getForceUpdateStateUseCase()) {
                _viewState.update { it.copy(directions = NavigationDirections.ForceUpdate) }
            }
            delay(500)
            _viewState.update { it.copy(isLoading = false) }
        }
        viewModelScope.launch {
            if (getIsOfflineModeUseCase()) {
                _viewState.update { it.copy(directions = NavigationDirections.Dashboard(true)) }
            }
        }
    }
}
