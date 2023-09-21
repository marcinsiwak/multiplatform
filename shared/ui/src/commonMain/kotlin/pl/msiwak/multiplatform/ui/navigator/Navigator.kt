package pl.msiwak.multiplatform.ui.navigator

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Navigator {
    private val _commands = MutableSharedFlow<NavigationDirections>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val commands: SharedFlow<NavigationDirections> = _commands.asSharedFlow()
    fun navigate(directions: NavigationDirections) {
        if (directions.destination.isNotEmpty()) {
            _commands.tryEmit(directions)
        }
    }

    fun navigateUp() {
        _commands.tryEmit(NavigationDirections.NavigateUp)
    }
}
