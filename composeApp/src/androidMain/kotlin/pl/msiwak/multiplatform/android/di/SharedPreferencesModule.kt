package pl.msiwak.multiplatform.android.di

import android.app.Application
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.msiwak.multiplatform.utils.KMPPreferences
import pl.msiwak.multiplatform.utils.KMPPreferencesImpl

val sharedPreferencesModule = module {
    single { getSharedPrefs(androidApplication()) }
}

fun getSharedPrefs(androidApplication: Application): KMPPreferences {
    return KMPPreferencesImpl(androidApplication)
}
