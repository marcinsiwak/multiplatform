package pl.msiwak.multiplatform.android.ui.category

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import pl.msiwak.multiplatform.android.ui.components.InputView

@Composable
fun AddExerciseDialog(
    inputText: String = "",
    onExerciseTitleChanged: (String) -> Unit,
    onAddExerciseClicked: () -> Unit,
    onDialogClosed: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(text = "Add exercise title")
        },
        text = {
            InputView(value = inputText,
                onValueChange = {
                    onExerciseTitleChanged(it)
                })
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddExerciseClicked()
                }) {
                Text("Add exercise")
            }
        }
    )
}