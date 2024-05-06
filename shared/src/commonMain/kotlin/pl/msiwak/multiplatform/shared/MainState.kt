package pl.msiwak.multiplatform.shared

import pl.msiwak.multiplatform.navigator.NavigationDirections
import pl.msiwak.multiplatform.navigator.destination.NavDestination

data class MainState(
    val isLoading: Boolean = true,
    val directions: NavDestination = NavDestination.DashboardDestination.NavDashboardGraphDestination // todo when login will be available
)
