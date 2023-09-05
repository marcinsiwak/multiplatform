package pl.msiwak.multiplatform.android.ui.addCategory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.DropDownView
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.MR

@Composable
fun AddCategoryScreen() {
    val viewModel =
        koinViewModel<pl.msiwak.multiplatform.core.ui.addCategory.AddCategoryViewModel>()
    val state = viewModel.viewState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            InputView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.space_8),
                value = state.value.name,
                onValueChange = {
                    viewModel.onCategoryNameChanged(it)
                },
                hintText = stringResource(id = MR.strings.category_name.resourceId)
            )
            DropDownView(
                currentValue = state.value.exerciseType.name,
                items = ExerciseType.values().toList(),
                onItemPicked = {
                    viewModel.onTypePicked(it)
                })
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.dimens.space_16,
                    horizontal = MaterialTheme.dimens.space_80
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            onClick = { viewModel.onSaveCategoryClicked() }
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                text = "Add category",
                fontSize = MaterialTheme.font.font_16
            )
        }
    }
}

@Preview
@Composable
fun AddCategoryScreenPreview() {
    AddCategoryScreen()
}
