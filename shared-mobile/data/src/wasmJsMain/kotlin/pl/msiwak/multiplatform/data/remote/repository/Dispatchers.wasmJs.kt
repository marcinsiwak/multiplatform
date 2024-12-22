package pl.msiwak.multiplatform.data.remote.repository

import kotlin.coroutines.CoroutineContext

actual object Dispatchers {
    actual val Main: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.Main
    actual val IO: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.Default
    actual val Default: CoroutineContext
        get() = kotlinx.coroutines.Dispatchers.Default
}
