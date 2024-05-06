package pl.msiwak.multiplatform.ui.addCategory

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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.DropDownView
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.Loader

@Composable
fun AddCategoryScreen(
    navController: NavController,
    viewModel: AddCategoryViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    AddCategoryScreenContent(
        viewState = viewState,
        onCategoryNameChanged = viewModel::onCategoryNameChanged,
        onTypePicked = viewModel::onTypePicked,
        onSaveCategoryClicked = viewModel::onSaveCategoryClicked
    )
}

@Composable
fun AddCategoryScreenContent(
    viewState: State<AddCategoryState>,
    onCategoryNameChanged: (String) -> Unit = {},
    onTypePicked: (ExerciseType) -> Unit = {},
    onSaveCategoryClicked: () -> Unit = {}
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            InputView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.dimens.space_8),
                value = viewState.value.name,
                onValueChange = {
                    onCategoryNameChanged(it)
                },
                hintText = stringResource(SR.strings.category_name)
            )
            DropDownView(
                currentValue = viewState.value.exerciseType.name,
                items = ExerciseType.values().toList(),
                onItemPicked = {
                    onTypePicked(it)
                }
            )
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
            onClick = { onSaveCategoryClicked() }
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                text = stringResource(SR.strings.add_category),
                fontSize = MaterialTheme.font.font_16
            )
        }
    }
}

// @DarkLightPreview
// @Composable
// fun AddCategoryScreenPreview() {
//     AppTheme {
//         AddCategoryScreenContent(
//             viewState = MutableStateFlow(AddCategoryState()).collectAsState()
//         )
//     }
// }
