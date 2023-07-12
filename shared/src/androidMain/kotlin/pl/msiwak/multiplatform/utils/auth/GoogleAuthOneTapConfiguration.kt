package pl.msiwak.multiplatform.utils.auth

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import pl.msiwak.multiplatform.BuildKonfig

class GoogleAuthOneTapConfiguration {

    val signInRequest: BeginSignInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(BuildKonfig.GOOGLE_AUTH_WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()
}