package pl.msiwak.multiplatform.network

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

actual class EngineProvider actual constructor() {
    actual fun getEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return io.ktor.client.engine.js.JsClient()
    }
}