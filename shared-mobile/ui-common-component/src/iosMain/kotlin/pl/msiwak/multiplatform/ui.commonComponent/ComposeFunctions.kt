package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable
import cocoapods.FirebaseCore.FIRApp
import cocoapods.GoogleSignIn.GIDConfiguration
import cocoapods.GoogleSignIn.GIDSignIn
import cocoapods.GoogleSignIn.GIDSignInResult
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIApplication
import platform.UIKit.UIWindowScene

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String, String?) -> Unit): () -> Unit {
    val clientId = FIRApp.defaultApp()?.options?.clientID ?: return {}
    val signInConfig = GIDConfiguration(clientID = clientId)
    GIDSignIn.sharedInstance.configuration = signInConfig
    return {
        (UIApplication.sharedApplication.connectedScenes.first() as UIWindowScene).keyWindow?.rootViewController()
            ?.let {
                GIDSignIn.sharedInstance.signInWithPresentingViewController(
                    it
                ) { result: GIDSignInResult?, error: NSError? ->
                    val user = result?.user
                    val idToken = user?.idToken?.tokenString
                    val accessToken = user?.accessToken?.tokenString
                    if (idToken != null && accessToken != null) {
                        onResultOk(idToken, accessToken)
                    }
                }
            }
    }
}
