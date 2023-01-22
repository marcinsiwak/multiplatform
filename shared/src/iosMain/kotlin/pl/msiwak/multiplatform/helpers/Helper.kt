package pl.msiwak.multiplatform.helpers

import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.di.appModule

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}