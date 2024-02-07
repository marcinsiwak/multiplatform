package pl.msiwak.multiplatform.core.main

import pl.msiwak.multiplatform.navigator.NavigationDirections

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavigationDirections = NavigationDirections.Dashboard() // todo when login will be available
)
