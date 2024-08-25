package pl.msiwak

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pl.msiwak.infrastructure.config.auth.firebase.configureFirebaseAuth
import pl.msiwak.infrastructure.config.configureRouting
import pl.msiwak.infrastructure.config.initialConfiguration
import pl.msiwak.infrastructure.di.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    initialConfiguration()

    install(Koin) {
        slf4jLogger()
        modules(
            diModule,
            diMapperModule,
            diRepositoryModule,
            diControllerModule,
            diDaoModule
        )
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    configureFirebaseAuth()
    configureRouting()
}
