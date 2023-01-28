package pl.msiwak.multiplatform.helpers

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.di.appModule

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}