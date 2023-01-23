package pl.msiwak.multiplatform.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import pl.msiwak.multiplatform.android.di.androidModule
import pl.msiwak.multiplatform.android.di.useCaseModule
import pl.msiwak.multiplatform.di.appModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule() + androidModule + useCaseModule)
            androidContext(this@App)

        }
    }
}