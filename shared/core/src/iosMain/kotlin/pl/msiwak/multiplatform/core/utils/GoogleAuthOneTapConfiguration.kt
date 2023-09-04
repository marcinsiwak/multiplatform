package pl.msiwak.multiplatform.core.utils

import cocoapods.FirebaseCore.FIRApp
import cocoapods.GoogleSignIn.GIDConfiguration
import cocoapods.GoogleSignIn.GIDSignIn

class GoogleAuthOneTapConfiguration {
    fun setConfig() {
        val clientId = FIRApp.defaultApp()?.options?.clientID ?: return
        val signInConfig = GIDConfiguration(clientID = clientId)
        GIDSignIn.sharedInstance.configuration = signInConfig
    }
}