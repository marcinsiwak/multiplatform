package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import pl.msiwak.multiplatform.MR

@Composable
fun PopupDialog(
    title: String = "",
    description: String = "",
    confirmButtonTitle: String = "",
    dismissButtonTitle: String = "",
    onDialogClosed: () -> Unit = {},
    onConfirmClicked: () -> Unit = {},
    onDismissClicked: () -> Unit = {}
) {
    AlertDialog(
        containerColor = colorResource(MR.colors.gray.resourceId),
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary
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
                })
        },
        dismissButton = {
            SecondaryButton(
                text = dismissButtonTitle,
                onClick = {
                    onDismissClicked()
                })
        }
    )
}