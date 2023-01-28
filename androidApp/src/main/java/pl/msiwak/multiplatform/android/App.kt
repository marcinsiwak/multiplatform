package pl.msiwak.multiplatform.android

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import pl.msiwak.multiplatform.android.di.androidModule
import pl.msiwak.multiplatform.di.appModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())

        startKoin {
            modules(appModule() + androidModule)
            androidContext(this@App)
        }
    }
}