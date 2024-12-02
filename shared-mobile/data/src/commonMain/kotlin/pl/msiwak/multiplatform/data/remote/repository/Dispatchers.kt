package pl.msiwak.multiplatform.data.remote.repository

expect object Dispatchers {
    val Main: kotlin.coroutines.CoroutineContext
    val IO: kotlin.coroutines.CoroutineContext
    val Default: kotlin.coroutines.CoroutineContext
}