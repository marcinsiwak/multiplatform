package pl.msiwak.multiplatform.shared.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.domain.authorization.GetUserTokenUseCase
import pl.msiwak.multiplatform.domain.authorization.ObserveAuthStateChangedUseCase
import pl.msiwak.multiplatform.domain.remoteConfig.FetchRemoteConfigUseCase
import pl.msiwak.multiplatform.domain.user.GetUserUseCase
import pl.msiwak.multiplatform.domain.version.GetForceUpdateStateUseCase
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.permissionmanager.AppPermission
import pl.msiwak.multiplatform.permissionmanager.PermissionBridge
import pl.msiwak.multiplatform.shared.navigation.NavigationProvider
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class MainViewModel(
    fetchRemoteConfigUseCase: FetchRemoteConfigUseCase,
    getForceUpdateStateUseCase: GetForceUpdateStateUseCase,
    globalErrorHandler: GlobalErrorHandler,
    getUserTokenUseCase: GetUserTokenUseCase,
    observeAuthStateChangedUseCase: ObserveAuthStateChangedUseCase,
    private val getUserUseCase: GetUserUseCase,
    val navigationProvider: NavigationProvider,
    private val permissionBridge: PermissionBridge
) : ViewModel() {

    private val errorHandler = globalErrorHandler.handleError()
    private val getUserErrorHandler = globalErrorHandler.handleError {
        _viewState.update { it.copy(directions = NavDestination.WelcomeDestination.NavWelcomeGraphDestination) }
        true
    }

    private val _viewState = MutableStateFlow(MainState())
    val viewState: StateFlow<MainState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch(errorHandler) {
            observeAuthStateChangedUseCase()
        }
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            fetchRemoteConfigUseCase()
            permissionBridge.requestPermission(AppPermission.NOTIFICATIONS)

            if (getForceUpdateStateUseCase()) {
                _viewState.update { it.copy(directions = NavDestination.ForceUpdateDestination.NavForceUpdateGraphDestination) }
            }
            delay(500)
            _viewState.update { it.copy(isLoading = false) }
        }

        viewModelScope.launch(getUserErrorHandler) {
            if (!getUserTokenUseCase().isNullOrEmpty()) {
                getUserUseCase()
                _viewState.update { it.copy(directions = NavDestination.DashboardDestination.NavDashboardGraphDestination) }
            }
        }
    }
}
