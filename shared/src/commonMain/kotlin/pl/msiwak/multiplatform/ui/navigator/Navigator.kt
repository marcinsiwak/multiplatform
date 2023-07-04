package pl.msiwak.multiplatform.ui.navigator

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.msiwak.multiplatform.CommonSharedFlow
import pl.msiwak.multiplatform.asCommonFlow

class Navigator {
    private val _commands = MutableSharedFlow<NavigationDirections>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val commands: CommonSharedFlow<NavigationDirections> = _commands.asCommonFlow()
    fun navigate(directions: NavigationDirections) {
        if (directions.destination.isNotEmpty()){
            _commands.tryEmit(directions)
        }
    }

    fun navigateUp() {
        _commands.tryEmit(NavigationDirections.NavigateUp)
    }
}
