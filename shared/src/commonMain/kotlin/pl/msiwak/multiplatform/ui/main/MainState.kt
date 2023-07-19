package pl.msiwak.multiplatform.ui.main

import pl.msiwak.multiplatform.ui.navigator.NavigationDirections

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavigationDirections = NavigationDirections.Welcome()
)
