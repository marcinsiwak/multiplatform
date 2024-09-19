package pl.msiwak.multiplatform.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual class EngineProvider {
    actual fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = CIO
}
