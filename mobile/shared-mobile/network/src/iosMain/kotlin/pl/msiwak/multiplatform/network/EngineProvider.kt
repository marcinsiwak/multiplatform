package pl.msiwak.multiplatform.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual class EngineProvider {
    actual fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
}
