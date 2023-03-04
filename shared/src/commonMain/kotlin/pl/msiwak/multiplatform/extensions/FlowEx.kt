package pl.msiwak.multiplatform.extensions

import com.squareup.sqldelight.Query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    val query: Query<T> = this@asFlow
    emit(query)

    val channel = Channel<Unit>(Channel.CONFLATED)

    val listener = object : Query.Listener {
        override fun queryResultsChanged() {
            channel.trySend(Unit)
        }
    }
    addListener(listener)

    try {
        for (item in channel) {
            emit(query)
        }
    } finally {
        removeListener(listener)
    }
}