package pl.msiwak.multiplatform.ui.commonComponent

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import io.github.aakira.napier.Napier
import pl.msiwak.multiplatform.android.extensions.findActivity

@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String) -> Unit): () -> Unit {
    val context = LocalContext.current.findActivity()

    val oneTapClient: SignInClient = remember {
        Identity.getSignInClient(context)
    }
    val signInRequest: BeginSignInRequest = remember {
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("607279059338-tf6mpi0ac6fdecs7jq0b05pkvvnirdmd.apps.googleusercontent.com")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    val googleAuthContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken ?: return@rememberLauncherForActivityResult
                onResultOk(idToken)
            }
        }
    )

    return {
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                googleAuthContract.launch(
                    IntentSenderRequest
                        .Builder(result.pendingIntent.intentSender)
                        .build()
                )
            }
            .addOnFailureListener { e ->
                Napier.e("GOOGLE AUTH FAILED: $e")
            }
    }
}