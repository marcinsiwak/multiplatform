package pl.msiwak.multiplatform.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect class EngineProvider() {
    fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}
