package pl.msiwak.multiplatform.ui.addCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.add_category
import athletetrack.shared_mobile.commonresources.generated.resources.category_name
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.DropDownView
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun AddCategoryScreen(
    navController: NavController,
    viewModel: AddCategoryViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(true) {
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                is AddCategoryEvent.NavigateBack -> navController.navigateUp()
            }
        }
    }

    AddCategoryScreenContent(
        navController = navController,
        viewState = viewState,
        onUiAction = viewModel::onUiAction
    )
}

@Composable
fun AddCategoryScreenContent(
    navController: NavController,
    viewState: State<AddCategoryState>,
    onUiAction: (AddCategoryUiAction) -> Unit
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.add_category))
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(top = it.calculateTopPadding())
            ) {
                Column {
                    InputView(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(MaterialTheme.dimens.space_8),
                        value = viewState.value.name,
                        onValueChange = {
                            onUiAction(AddCategoryUiAction.OnCategoryNameChanged(it))
                        },
                        hintText = stringResource(Res.string.category_name)
                    )
                    DropDownView(
                        currentValue = viewState.value.exerciseType.name,
                        items = ExerciseType.entries,
                        onItemPicked = {
                            onUiAction(AddCategoryUiAction.OnTypePicked(it))
                        }
                    )
                }

                MainButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(
                            vertical = MaterialTheme.dimens.space_16,
                            horizontal = MaterialTheme.dimens.space_80
                        ),
                    onClick = { onUiAction(AddCategoryUiAction.OnSaveCategoryClicked) },
                    text = stringResource(Res.string.add_category)
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
fun AddCategoryScreenPreview() {
    AppTheme {
        AddCategoryScreenContent(
            rememberNavController(),
            viewState = MutableStateFlow(AddCategoryState()).collectAsState(),
            onUiAction = {}
        )
    }
}
