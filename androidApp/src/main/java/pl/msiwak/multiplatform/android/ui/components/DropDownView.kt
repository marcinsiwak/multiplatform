package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.example.library.MR
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.data.common.ExerciseType

@Composable
fun DropDownView(
    modifier: Modifier = Modifier,
    currentValue: String,
    items: List<ExerciseType>,
    onItemPicked: (ExerciseType) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var expand by remember { mutableStateOf(false) }
    Column {
        InputView(
            modifier = Modifier
                .padding(8.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.hasFocus) {
                        expand = true
                    }
                },
            value = currentValue,
            onValueChange = {},
            hintText = getString(LocalContext.current, MR.strings.exercise_type),
            readOnly = true,
            errorsEnabled = false,
            trailingIcon = { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null) }
        )
        DropdownMenu(
            modifier = modifier,
            expanded = expand,
            onDismissRequest = {
                expand = false
                focusManager.clearFocus()
            }) {
            items.forEach { exercise ->
                DropdownMenuItem(
                    text = { Text(text = exercise.name) },
                    onClick = {
                        onItemPicked(exercise)
                        expand = false
                        focusManager.clearFocus()
                    })
            }
        }
    }
}
