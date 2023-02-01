package pl.msiwak.multiplatform.android.di

import android.app.Application
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.msiwak.multiplatform.utils.KMMPreferences


val sharedPreferencesModule = module {
    single { getSharedPrefs(androidApplication()) }
}

fun getSharedPrefs(androidApplication: Application): KMMPreferences {
    return KMMPreferences(androidApplication)
}
