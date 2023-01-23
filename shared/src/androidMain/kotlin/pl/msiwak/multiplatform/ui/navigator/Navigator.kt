package pl.msiwak.multiplatform.ui.navigator

import kotlinx.coroutines.flow.MutableStateFlow

actual class Navigator {
    var commands = MutableStateFlow<NavigationCommand>(NavigationDirections.Welcome)

    actual fun navigate(directions: NavigationCommand) {
        commands.value = directions
    }
}