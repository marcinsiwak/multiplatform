package pl.msiwak.multiplatform.android.ui.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.example.library.MR
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.utils.getString

@Composable
fun AddExerciseDialog(
    inputText: String = "",
    onExerciseTitleChanged: (String) -> Unit,
    onAddExerciseClicked: () -> Unit,
    onDialogClosed: () -> Unit = {}
) {
    AlertDialog(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.DarkGray,
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(text = getString(LocalContext.current, MR.strings.exercise_name))
        },
        text = {
            InputView(value = inputText,
                onValueChange = {
                    onExerciseTitleChanged(it)
                })
        },
        confirmButton = {
            Button(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                onClick = {
                    onAddExerciseClicked()
                }) {
                Text(text = getString(LocalContext.current, MR.strings.add_new_exercise))
            }
        }
    )
}