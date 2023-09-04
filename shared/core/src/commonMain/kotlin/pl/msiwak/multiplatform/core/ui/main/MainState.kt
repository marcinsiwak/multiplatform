package pl.msiwak.multiplatform.core.ui.main

import pl.msiwak.multiplatform.core.ui.navigator.NavigationDirections

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavigationDirections = NavigationDirections.Welcome()
)
