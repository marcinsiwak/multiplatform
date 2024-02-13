package pl.msiwak.multiplatform.android.ui.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.SecondaryButton
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.utils.DarkLightPreview
import pl.msiwak.multiplatform.commonResources.SR

@Composable
fun AddExerciseDialog(
    inputText: String = "",
    onExerciseTitleChanged: (String) -> Unit = { _ -> },
    onAddExerciseClicked: () -> Unit = {},
    onDialogClosed: () -> Unit = {}
) {
    AlertDialog(
        shape = RoundedCornerShape(MaterialTheme.dimens.dialog_corners),
        containerColor = colorResource(SR.colors.gray.resourceId),
        onDismissRequest = {
            onDialogClosed()
        },
        title = {
            Text(
                text = stringResource(SR.strings.exercise_name.resourceId),
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
                text = stringResource(SR.strings.add_new_exercise.resourceId)
            )
        }
    )
}

@DarkLightPreview
@Composable
fun AddExerciseDialogPreview() {
    AppTheme {
        AddExerciseDialog()
    }
}
