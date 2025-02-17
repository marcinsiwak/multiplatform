package pl.msiwak

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import kotlinx.serialization.json.Json
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.postgresql.util.PSQLException
import pl.msiwak.infrastructure.config.auth.configureAuthorization
import pl.msiwak.infrastructure.config.configureRouting
import pl.msiwak.infrastructure.config.initialConfiguration
import pl.msiwak.infrastructure.di.diControllerModule
import pl.msiwak.infrastructure.di.diDaoModule
import pl.msiwak.infrastructure.di.diMapperModule
import pl.msiwak.infrastructure.di.diModule
import pl.msiwak.infrastructure.di.diRepositoryModule
import pl.msiwak.infrastructure.di.diUtilsModule
import pl.msiwak.infrastructure.di.servicesModule
import pl.msiwak.multiplatform.shared.common.CustomHttpHeaders
import pl.msiwak.multiplatform.shared.exception.UserNotFoundException

fun main(args: Array<String>) {
    EngineMain.main(args)
}

@Suppress("LongMethod")
fun Application.module() {
    initialConfiguration()

    install(Koin) {
        slf4jLogger()
        modules(
            diModule,
            diMapperModule,
            diRepositoryModule,
            diControllerModule,
            diDaoModule,
            diUtilsModule,
            servicesModule
        )
    }
    install(ContentNegotiation) {
        json(
            json = Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )

        register(
            ContentType.Text.Html,
            KotlinxSerializationConverter(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        )
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.AccessControlAllowHeaders)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.AccessControlAllowMethods)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(CustomHttpHeaders.API_KEY_HEADER)
        allowHeader(CustomHttpHeaders.API_KEY_NONCE_HEADER)
        allowHeader(CustomHttpHeaders.API_KEY_TIMESTAMP_HEADER)
        allowHeader(CustomHttpHeaders.PERMISSION_POLICY_HEADER)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            when (cause) {
                is PSQLException -> {
                    if (cause.message?.contains("duplicate key value violates unique constraint") == true) {
                        call.respond(HttpStatusCode.Conflict, "Entry already exists")
                    } else {
                        call.respond(HttpStatusCode.InternalServerError, "Database error: ${cause.message}")
                    }
                }

                is UserNotFoundException -> call.respond(HttpStatusCode.NotFound)

                else -> call.respond(HttpStatusCode.InternalServerError, "Database error: ${cause.message}")
            }
        }
    }

    configureAuthorization()
    configureRouting()
}
