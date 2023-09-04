package pl.msiwak.multiplatform.core

import kotlin.native.internal.GC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

actual open class ViewModel actual constructor() {
    protected actual val viewModelScope: CoroutineScope = createViewModelScope()

    actual open fun onCleared() {
        viewModelScope.cancel()

        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
    }
}