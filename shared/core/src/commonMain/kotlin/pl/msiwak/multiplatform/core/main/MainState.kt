package pl.msiwak.multiplatform.core.main

import pl.msiwak.multiplatform.navigator.NavigationDirections

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavigationDirections = NavigationDirections.Welcome()
)
