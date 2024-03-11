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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.color
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.ListItemView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.extension.lifecycleObserver

@Composable
fun CategoryScreen(
    id: String,
    viewModel: CategoryViewModel = koinInject { parametersOf(id) }
) {
    val viewState = viewModel.viewState.collectAsState()

    val backgroundId = when (viewState.value.exerciseType) {
        ExerciseType.RUNNING -> SR.images.bg_running_field
        ExerciseType.GYM -> SR.images.bg_gym
    }

    LaunchedEffect(Unit) {
        lifecycleObserver.collect { event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> viewModel.onResume()
                else -> Unit
            }
        }
    }

    CategoryScreenContent(
        viewState = viewState,
        backgroundId = backgroundId,
        onConfirmClicked = viewModel::onResultRemoved,
        onDismissClicked = viewModel::onPopupDismissed,
        onExerciseTitleChanged = viewModel::onAddExerciseNameChanged,
        onAddExerciseClicked = viewModel::onAddExerciseClicked,
        onDialogClosed = viewModel::onDialogClosed,
        onItemClick = viewModel::onExerciseClicked,
        onLongClick = viewModel::onResultLongClicked,
        onClick = viewModel::onAddNewExerciseClicked

    )
}

@Suppress("MagicNumber")
@Composable
fun CategoryScreenContent(
    viewState: State<CategoryState>,
    backgroundId: ImageResource,
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
            title = stringResource(SR.strings.remove_result_dialog_title),
            description = stringResource(SR.strings.remove_result_dialog_description),
            confirmButtonTitle = stringResource(SR.strings.yes),
            dismissButtonTitle = stringResource(SR.strings.no),
            onConfirmClicked = onConfirmClicked,
            onDismissClicked = onDismissClicked
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (viewState.value.isDialogVisible) {
            AddExerciseDialog(
                inputText = viewState.value.newExerciseName,
                onExerciseTitleChanged = onExerciseTitleChanged,
                onAddExerciseClicked = onAddExerciseClicked,
                onDialogClosed = onDialogClosed
            )
        }

        Column {
            val shadowColor = MaterialTheme.color.ShadowColor
            Image(
                modifier = Modifier
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, shadowColor),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.space_264),
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
                text = stringResource(SR.strings.add_exercise),
                fontSize = MaterialTheme.font.font_16
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(
                        topStart = MaterialTheme.dimens.space_8,
                        bottomEnd = MaterialTheme.dimens.space_8
                    )
                )
                .padding(
                    horizontal = MaterialTheme.dimens.space_12,
                    vertical = MaterialTheme.dimens.space_8
                ),
            text = viewState.value.categoryName,
            fontSize = MaterialTheme.font.font_14,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

// @DarkLightPreview
// @Composable
// fun CategoryScreenPreview() {
//     AppTheme {
//         CategoryScreenContent(
//             viewState = MutableStateFlow(CategoryState()).collectAsState(),
//             backgroundId = SR.images.bg_gym.drawableResId
//         )
//     }
// }
