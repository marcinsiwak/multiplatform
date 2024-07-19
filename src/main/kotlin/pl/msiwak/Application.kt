package pl.msiwak

import io.ktor.server.application.*
import pl.msiwak.plugins.configureRouting

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
}
