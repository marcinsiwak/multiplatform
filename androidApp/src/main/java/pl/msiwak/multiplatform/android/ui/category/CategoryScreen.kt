package pl.msiwak.multiplatform.android.ui.category

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.MR
import pl.msiwak.multiplatform.android.ui.components.ListItemView
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.theme.color
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.ui.category.CategoryViewModel

@Composable
fun CategoryScreen(id: Long) {
    val viewModel = koinViewModel<CategoryViewModel> { parametersOf(id) }
    val state = viewModel.viewState.collectAsState()

    val backgroundId = when (state.value.exerciseType) { //todo maybe share with ios
        ExerciseType.RUNNING -> MR.images.bg_running_field.drawableResId
        ExerciseType.GYM -> MR.images.bg_gym.drawableResId
//        ExerciseType.OTHER -> null
    }

    if (state.value.isRemoveExerciseDialogVisible) {
        PopupDialog(title = stringResource(MR.strings.remove_result_dialog_title.resourceId),
            description = stringResource(MR.strings.remove_result_dialog_description.resourceId),
            confirmButtonTitle = stringResource(MR.strings.yes.resourceId),
            dismissButtonTitle = stringResource(MR.strings.no.resourceId),
            onConfirmClicked = {
                viewModel.onResultRemoved()
            },
            onDismissClicked = {
                viewModel.onPopupDismissed()
            })
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        if (state.value.isDialogVisible) {
            AddExerciseDialog(inputText = state.value.newExerciseName, onExerciseTitleChanged = {
                viewModel.onAddExerciseNameChanged(it)
            }, onAddExerciseClicked = {
                viewModel.onAddExerciseClicked()
            }, onDialogClosed = {
                viewModel.onDialogClosed()
            })
        }

        Column {
            val shadowColor = MaterialTheme.color.ShadowColor
            Image(modifier = Modifier
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
                painter = painterResource(id = backgroundId),
                contentScale = ContentScale.Crop,
                contentDescription = "category background")
            LazyColumn {
                itemsIndexed(state.value.exerciseList) { index, item ->
                    ListItemView(name = item.name,
                        onItemClick = { viewModel.onExerciseClicked(item.id) },
                        onLongClick = { viewModel.onResultLongClicked(index) })
                }
            }
        }
        Button(modifier = Modifier
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
            onClick = { viewModel.onAddNewExerciseClicked() }) {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                text = "Add exercise",
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
            text = state.value.categoryName,
            fontSize = MaterialTheme.font.font_14,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(0)
}
