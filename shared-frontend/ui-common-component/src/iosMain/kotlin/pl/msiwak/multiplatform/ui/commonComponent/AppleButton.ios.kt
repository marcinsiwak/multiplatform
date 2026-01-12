package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.ic_apple_logo
import athletetrack.shared_frontend.commonresources.generated.resources.welcome_apple_login
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.color

private val manager = AppleSignInManager()

@Composable
actual fun AppleButton(modifier: Modifier, callback: (String?, String?, String?) -> Unit) {
    val scope = rememberCoroutineScope()
    Column {
        MainButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    manager.signIn { tokenString: String?, nonce: String?, error: String? ->
                        callback(tokenString, nonce, error)
                    }
                }
            },
            leadingIcon = Res.drawable.ic_apple_logo,
            iconTint = MaterialTheme.color.AppleIcon,
            text = stringResource(Res.string.welcome_apple_login)
        )
    }
}