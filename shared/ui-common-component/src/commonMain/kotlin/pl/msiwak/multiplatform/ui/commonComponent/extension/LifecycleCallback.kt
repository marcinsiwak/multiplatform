package pl.msiwak.multiplatform.ui.commonComponent.extension

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

private val _lifecycleObserver = MutableSharedFlow<Lifecycle.Event>(
    replay = 0,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
    extraBufferCapacity = 1
)
val lifecycleObserver: SharedFlow<Lifecycle.Event> = _lifecycleObserver.asSharedFlow()

fun lifecycleCallback(lifecycle: Lifecycle.Event) {
    _lifecycleObserver.tryEmit(lifecycle)
    println(lifecycle.name)
}