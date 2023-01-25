package pl.msiwak.multiplatform.ui.navigator

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import pl.msiwak.multiplatform.CommonSharedFlow
import pl.msiwak.multiplatform.asCommonFlow

class Navigator {
    private val _commands = MutableSharedFlow<NavigationCommand>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val commands: CommonSharedFlow<NavigationCommand> = _commands.asCommonFlow()
    fun navigate(directions: NavigationCommand) {
        _commands.tryEmit(directions)
    }
}
