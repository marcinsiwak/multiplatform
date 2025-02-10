package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.network.service.UserService

val serviceModule = module {
    single { UserService(get(), get()) }
    single { CategoryService(get(), get(), get(), get()) }
}