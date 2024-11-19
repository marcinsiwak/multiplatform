group = "pl.msiwak.shared"
version = "1.0.0"

plugins {
    alias(sharedLibs.plugins.kotlinMultiplatform).apply(false)
    alias(sharedLibs.plugins.serialization).apply(false)
}
