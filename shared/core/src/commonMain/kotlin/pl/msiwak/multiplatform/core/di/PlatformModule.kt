package pl.msiwak.multiplatform.core.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.core.Platform

val platformModule = module {
    single { Platform() }
}