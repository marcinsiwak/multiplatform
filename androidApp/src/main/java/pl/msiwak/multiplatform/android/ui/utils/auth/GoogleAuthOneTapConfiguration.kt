package pl.msiwak.multiplatform.android.ui.utils.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import pl.msiwak.multiplatform.android.BuildConfig

class GoogleAuthOneTapConfiguration {

    val signInRequest: BeginSignInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(BuildConfig.GOOGLE_AUTH_WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()
}
