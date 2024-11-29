package pl.msiwak

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pl.msiwak.infrastructure.config.auth.firebase.configureFirebaseAuth
import pl.msiwak.infrastructure.config.configureRouting
import pl.msiwak.infrastructure.config.initialConfiguration
import pl.msiwak.infrastructure.di.diControllerModule
import pl.msiwak.infrastructure.di.diDaoModule
import pl.msiwak.infrastructure.di.diMapperModule
import pl.msiwak.infrastructure.di.diModule
import pl.msiwak.infrastructure.di.diRepositoryModule

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
        json(
            json = Json {
                prettyPrint = true
                isLenient = true
            }
        )
    }
    configureFirebaseAuth()
    configureRouting()
}
