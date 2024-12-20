package pl.msiwak.multiplatform.android

import android.app.Application
import com.google.android.gms.ads.MobileAds
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import pl.msiwak.multiplatform.android.di.androidDatabaseModule
import pl.msiwak.multiplatform.android.di.androidRepositoryModule
import pl.msiwak.multiplatform.android.di.sharedPreferencesModule
import pl.msiwak.multiplatform.shared.appModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this)

        startKoin {
            modules(appModule() + sharedPreferencesModule + androidDatabaseModule + androidRepositoryModule)
            androidContext(this@App)
        }
    }
}
