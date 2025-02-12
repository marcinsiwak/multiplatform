package pl.msiwak.multiplatform.commonObject.dispatcher

import kotlinx.coroutines.IO
import kotlin.coroutines.CoroutineContext

actual object Dispatchers {
    actual val Main: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.Main
    actual val IO: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.IO
    actual val Default: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.Default
}
