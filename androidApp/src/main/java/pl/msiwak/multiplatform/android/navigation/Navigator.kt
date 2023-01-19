package pl.msiwak.multiplatform.android.navigation

import kotlinx.coroutines.flow.MutableStateFlow

class Navigator {

    var commands = MutableStateFlow<NavigationCommand>(NavigationDirections.Welcome)

    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }

}