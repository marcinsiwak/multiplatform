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
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString

@Composable
fun AddExerciseDialog(
    inputText: String = "",
    onExerciseTitleChanged: (String) -> Unit,
    onAddExerciseClicked: () -> Unit,
    onDialogClosed: () -> Unit = {}
) {
    val dimens = LocalDim.current
    val context = LocalContext.current

    AlertDialog(
        shape = RoundedCornerShape(dimens.space_16),
        backgroundColor = Color.DarkGray,
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(text = getString(context, MR.strings.exercise_name))
        },
        text = {
            InputView(value = inputText,
                onValueChange = {
                    onExerciseTitleChanged(it)
                })
        },
        confirmButton = {
            Button(
                shape = RoundedCornerShape(dimens.space_8),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = dimens.space_16, end = dimens.space_16, bottom = dimens.space_16),
                onClick = {
                    onAddExerciseClicked()
                }) {
                Text(text = getString(context, MR.strings.add_new_exercise))
            }
        }
    )
}