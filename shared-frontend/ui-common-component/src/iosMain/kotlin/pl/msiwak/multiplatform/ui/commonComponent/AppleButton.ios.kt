package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun AppleButton(modifier: Modifier, callback: (String, String) -> Unit) {
    Column {
        MainButton(modifier = Modifier.fillMaxWidth(), onClick = {
            AppleSignInManager().signIn { tokenString: String, nonce: String ->
                callback(tokenString, nonce)
            }
        }, text = "Test")
    }
}