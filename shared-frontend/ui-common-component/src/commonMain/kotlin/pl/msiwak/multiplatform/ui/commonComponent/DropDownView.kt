package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.category
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.dimens

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DropDownView(
    modifier: Modifier = Modifier,
    currentValue: String,
    items: List<ExerciseType>,
    onItemPicked: (ExerciseType) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var expand by remember { mutableStateOf(false) }
    Column {
        InputView(
            modifier = modifier
                .padding(MaterialTheme.dimens.space_8)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.hasFocus) {
                        expand = true
                    }
                },
            value = currentValue,
            onValueChange = {},
            hintText = stringResource(Res.string.category),
            readOnly = true,
            errorsEnabled = false,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        )
        DropdownMenu(
            modifier = Modifier,
            offset = DpOffset(x = MaterialTheme.dimens.space_8, 0.dp),
            expanded = expand,
            onDismissRequest = {
                expand = false
                focusManager.clearFocus()
            }
        ) {
            items.fastForEach { exercise ->
                DropdownMenuItem(
                    text = { Text(text = exercise.name) },
                    onClick = {
                        onItemPicked(exercise)
                        expand = false
                        focusManager.clearFocus()
                    }
                )
            }
        }
    }
}
