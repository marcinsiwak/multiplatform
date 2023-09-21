package pl.msiwak.multiplatform.database.extension

import app.cash.sqldelight.Query
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T : Any> Query<T>.asFlow(): Flow<Query<T>> = flow {
    val query: Query<T> = this@asFlow
    emit(query)

    val channel = Channel<Unit>(Channel.CONFLATED)

    val listener = Query.Listener { channel.trySend(Unit) }
    addListener(listener)

    try {
        for (item in channel) {
            emit(query)
        }
    } finally {
        removeListener(listener)
    }
}