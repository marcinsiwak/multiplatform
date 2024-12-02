package pl.msiwak.multiplatform.data.remote.repository

import kotlin.coroutines.CoroutineContext

actual object Dispatchers {
    actual val Main: CoroutineContext
        get() = Dispatchers.Main
    actual val IO: CoroutineContext
        get() = Dispatchers.IO
    actual val Default: CoroutineContext
        get() = Dispatchers.Default
}