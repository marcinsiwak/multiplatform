package pl.msiwak.multiplatform.shared

import pl.msiwak.multiplatform.navigator.destination.NavDestination

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavDestination = NavDestination.WelcomeDestination.NavWelcomeGraphDestination
)
