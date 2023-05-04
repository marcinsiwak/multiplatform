package pl.msiwak.multiplatform

import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> SharedFlow<T>.asCommonFlow(): CommonSharedFlow<T> = CommonSharedFlow(this)
class CommonSharedFlow<T>(private val origin: SharedFlow<T>) : SharedFlow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()
        distinctUntilChanged()
        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}