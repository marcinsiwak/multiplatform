@file:OptIn(ExperimentalResourceApi::class)

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.add_exercise
import athletetrack.shared.commonresources.generated.resources.bg_gym
import athletetrack.shared.commonresources.generated.resources.bg_running_field
import athletetrack.shared.commonresources.generated.resources.no
import athletetrack.shared.commonresources.generated.resources.remove_result_dialog_description
import athletetrack.shared.commonresources.generated.resources.remove_result_dialog_title
import athletetrack.shared.commonresources.generated.resources.yes
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.color
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.ListItemView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

@OptIn(ExperimentalResourceApi::class)
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
        onConfirmClicked = viewModel::onResultRemoved,
        onDismissClicked = viewModel::onPopupDismissed,
        onExerciseTitleChanged = viewModel::onAddExerciseNameChanged,
        onAddExerciseClicked = viewModel::onAddExerciseClicked,
        onDialogClosed = viewModel::onDialogClosed,
        onItemClick = {
            navController.navigate(
                NavDestination.AddExerciseDestination.NavAddExerciseScreen.route(
                    it
                )
            )
        },
        onLongClick = viewModel::onResultLongClicked,
        onClick = viewModel::onAddNewExerciseClicked

    )
}

@Suppress("MagicNumber")
@Composable
fun CategoryScreenContent(
    navController: NavController,
    viewState: State<CategoryState>,
    backgroundId: DrawableResource,
    onConfirmClicked: () -> Unit = {},
    onDismissClicked: () -> Unit = {},
    onExerciseTitleChanged: (String) -> Unit = {},
    onAddExerciseClicked: () -> Unit = {},
    onDialogClosed: () -> Unit = {},
    onItemClick: (String) -> Unit = {},
    onLongClick: (Int) -> Unit = {},
    onClick: () -> Unit = {}
) {
    if (viewState.value.isRemoveExerciseDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.remove_result_dialog_title),
            description = stringResource(Res.string.remove_result_dialog_description),
            confirmButtonTitle = stringResource(Res.string.yes),
            dismissButtonTitle = stringResource(Res.string.no),
            onConfirmClicked = onConfirmClicked,
            onDismissClicked = onDismissClicked
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    if (viewState.value.isDialogVisible) {
        AddExerciseDialog(
            inputText = viewState.value.newExerciseName,
            onExerciseTitleChanged = onExerciseTitleChanged,
            onAddExerciseClicked = onAddExerciseClicked,
            onDialogClosed = onDialogClosed
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
                    .padding(top = it.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column {
                    val shadowColor = MaterialTheme.color.ShadowColor
                    Image(
                        modifier = Modifier
                            .drawWithCache {
                                val gradientTop = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, shadowColor),
                                    startY = size.height / 6,
                                    endY = 0f
                                )
                                val gradientBottom = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, shadowColor),
                                    startY = size.height / 3,
                                    endY = size.height
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(gradientTop, blendMode = BlendMode.Multiply)
                                    drawRect(gradientBottom, blendMode = BlendMode.Multiply)
                                }
                            }
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
                                onItemClick = { onItemClick(item.id) },
                                onLongClick = { onLongClick(index) }
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(
                            vertical = MaterialTheme.dimens.space_16,
                            horizontal = MaterialTheme.dimens.space_80
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    onClick = onClick
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                        text = stringResource(Res.string.add_exercise),
                        fontSize = MaterialTheme.font.font_16
                    )
                }
            }
        }
    )
}

// @DarkLightPreview
// @Composable
// fun CategoryScreenPreview() {
//     AppTheme {
//         CategoryScreenContent(
//             viewState = MutableStateFlow(CategoryState()).collectAsState(),
//             backgroundId = Res.drawable.bg_gym.drawableResId
//         )
//     }
// }
