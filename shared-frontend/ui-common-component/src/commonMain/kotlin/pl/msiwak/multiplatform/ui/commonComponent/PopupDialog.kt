package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PopupDialog(
    title: String = "",
    description: String = "",
    confirmButtonTitle: String,
    dismissButtonTitle: String? = null,
    onDialogClosed: () -> Unit = {},
    onConfirmClicked: () -> Unit = {},
    onDismissClicked: () -> Unit = {}
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.tertiary,
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onTertiary
            )
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            SecondaryButton(
                text = confirmButtonTitle,
                onClick = {
                    onConfirmClicked()
                }
            )
        },
        dismissButton = if (dismissButtonTitle != null) {
            {
                SecondaryButton(
                    text = dismissButtonTitle,
                    onClick = {
                        onDismissClicked()
                    }
                )
            }
        } else {
            null
        }
    )
}
