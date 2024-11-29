package pl.msiwak.multiplatform.ui.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.add_exercise
import athletetrack.shared_mobile.commonresources.generated.resources.bg_gym
import athletetrack.shared_mobile.commonresources.generated.resources.bg_running_field
import athletetrack.shared_mobile.commonresources.generated.resources.no
import athletetrack.shared_mobile.commonresources.generated.resources.remove_result_dialog_description
import athletetrack.shared_mobile.commonresources.generated.resources.remove_result_dialog_title
import athletetrack.shared_mobile.commonresources.generated.resources.yes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.ListItemView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

@Composable
fun CategoryScreen(
    navController: NavController,
    id: String,
    viewModel: CategoryViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val backgroundId = when (viewState.value.exerciseType) {
        ExerciseType.RUNNING -> Res.drawable.bg_running_field
        ExerciseType.GYM -> Res.drawable.bg_gym
    }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.onResume()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onInit(id)
        viewModel.viewEvent.collectLatest { event ->
            when (event) {
                is CategoryEvent.NavigateToAddExercise -> navController.navigate(
                    NavDestination.AddExerciseDestination.NavAddExerciseScreen.route(event.id)
                )
            }
        }
    }

    CategoryScreenContent(
        navController = navController,
        viewState = viewState,
        backgroundId = backgroundId,
        onUiAction = viewModel::onUiAction
    )
}

@Suppress("MagicNumber")
@Composable
fun CategoryScreenContent(
    navController: NavController,
    viewState: State<CategoryState>,
    backgroundId: DrawableResource,
    onUiAction: (CategoryUiAction) -> Unit
) {
    if (viewState.value.isRemoveExerciseDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.remove_result_dialog_title),
            description = stringResource(Res.string.remove_result_dialog_description),
            confirmButtonTitle = stringResource(Res.string.yes),
            dismissButtonTitle = stringResource(Res.string.no),
            onConfirmClicked = { onUiAction(CategoryUiAction.OnConfirmClick) },
            onDismissClicked = { onUiAction(CategoryUiAction.OnDismissClicked) }
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    if (viewState.value.isDialogVisible) {
        AddExerciseDialog(
            inputText = viewState.value.newExerciseName,
            onExerciseTitleChanged = { onUiAction(CategoryUiAction.OnExerciseTitleChanged(it)) },
            onAddExerciseClicked = { onUiAction(CategoryUiAction.OnAddExerciseClicked) },
            onDialogClosed = { onUiAction(CategoryUiAction.OnDialogClosed) }
        )
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, title = viewState.value.categoryName)
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
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(MaterialTheme.dimens.category_img_height),
                        painter = painterResource(backgroundId),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )

                    LazyColumn {
                        itemsIndexed(viewState.value.exerciseList) { index, item ->
                            ListItemView(
                                name = item.exerciseTitle,
                                onItemClick = { onUiAction(CategoryUiAction.OnItemClick(item.id)) },
                                onLongClick = { onUiAction(CategoryUiAction.OnLongClick(index)) }
                            )
                        }
                    }
                }

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(
                            vertical = MaterialTheme.dimens.space_16,
                            horizontal = MaterialTheme.dimens.space_80
                        ),
                    onClick = { onUiAction(CategoryUiAction.OnClick) },
                    text = stringResource(Res.string.add_exercise)
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
fun CategoryScreenPreview() {
    AppTheme {
        CategoryScreenContent(
            rememberNavController(),
            viewState = MutableStateFlow(CategoryState()).collectAsState(),
            backgroundId = Res.drawable.bg_running_field,
            onUiAction = { CategoryUiAction.OnClick }
        )
    }
}
