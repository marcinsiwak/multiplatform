package pl.msiwak.multiplatform.android.ui.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import dev.icerock.moko.resources.compose.colorResource
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
        containerColor = colorResource(resource = MR.colors.gray),
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(
                text = getString(context, MR.strings.exercise_name),
                color = Color.White
            )
        },
        text = {
            InputView(
                modifier = Modifier.padding(horizontal = dimens.space_16),
                value = inputText,
                onValueChange = {
                    onExerciseTitleChanged(it)
                })
        },
        confirmButton = {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimens.space_8, horizontal = dimens.space_16),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, contentColor = Color.Black
                ),
                onClick = { onAddExerciseClicked() }) {
                Text(text = getString(context, MR.strings.add_new_exercise))
            }
        }
    )
}