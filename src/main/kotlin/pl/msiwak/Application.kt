package pl.msiwak

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import pl.msiwak.auth.firebase.FirebaseAdmin
import pl.msiwak.database.DatabaseFactory
import pl.msiwak.di.diModule
import pl.msiwak.plugins.configureFirebaseAuth
import pl.msiwak.plugins.configureRouting

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
        configure = {
            FirebaseAdmin.init()
            DatabaseFactory.init()
        }
    ).start(wait = true)
}

fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(diModule)
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
