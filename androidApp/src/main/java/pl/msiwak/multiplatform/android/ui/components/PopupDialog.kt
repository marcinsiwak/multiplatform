package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

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
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = description)
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmClicked()
                }) {
                Text(confirmButtonTitle)
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismissClicked()
                }) {
                Text(dismissButtonTitle)
            }
        }
    )
}