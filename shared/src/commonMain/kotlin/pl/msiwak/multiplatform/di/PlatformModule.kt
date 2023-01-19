package pl.msiwak.multiplatform.di

import org.koin.dsl.module
import pl.msiwak.multiplatform.Platform

val platformModule = module {
    single { Platform() }
}