package pl.msiwak.multiplatform.ui.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.SecondaryButton

@Composable
fun AddExerciseDialog(
    inputText: String = "",
    onExerciseTitleChanged: (String) -> Unit = { _ -> },
    onAddExerciseClicked: () -> Unit = {},
    onDialogClosed: () -> Unit = {}
) {
    AlertDialog(
        shape = RoundedCornerShape(MaterialTheme.dimens.dialog_corners),
        containerColor = colorResource(SR.colors.gray),
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(
                text = stringResource(SR.strings.exercise_name),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        text = {
            InputView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.space_16),
                value = inputText,
                onValueChange = {
                    onExerciseTitleChanged(it)
                }
            )
        },
        confirmButton = {
            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.dimens.space_8,
                        horizontal = MaterialTheme.dimens.space_16
                    ),
                onClick = { onAddExerciseClicked() },
                text = stringResource(SR.strings.add_new_exercise)
            )
        }
    )
}

// @DarkLightPreview
// @Composable
// fun AddExerciseDialogPreview() {
//     AppTheme {
//         AddExerciseDialog()
//     }
// }