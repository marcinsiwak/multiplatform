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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.android.ui.components.ListItemView
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.ui.category.CategoryViewModel

@Composable
fun CategoryScreen(id: Long) {
    val viewModel = koinViewModel<CategoryViewModel> { parametersOf(id) }
    val state = viewModel.viewState.collectAsState()
    val dimens = LocalDim.current

    val backgroundId = when (state.value.exerciseType) { //todo maybe share with ios
        ExerciseType.RUNNING -> MR.images.bg_running_field.drawableResId
        ExerciseType.GYM -> MR.images.bg_gym.drawableResId
        ExerciseType.OTHER -> null
    }

    if (state.value.isRemoveExerciseDialogVisible) {
        PopupDialog(title = getString(LocalContext.current, MR.strings.remove_result_dialog_title),
            description = getString(
                LocalContext.current,
                MR.strings.remove_result_dialog_description
            ),
            confirmButtonTitle = getString(LocalContext.current, MR.strings.yes),
            dismissButtonTitle = getString(LocalContext.current, MR.strings.no),
            onConfirmClicked = {
                viewModel.onResultRemoved()
            },
            onDismissClicked = {
                viewModel.onPopupDismissed()
            })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
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
            backgroundId?.let {
                Image(modifier = Modifier
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = size.height / 3,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    }
                    .fillMaxWidth()
                    .height(dimens.space_264),
                    painter = painterResource(id = backgroundId),
                    contentScale = ContentScale.Crop,
                    contentDescription = "category background")
            }
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
            .padding(vertical = dimens.space_16, horizontal = dimens.space_80),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray, contentColor = Color.Black
            ),
            onClick = { viewModel.onAddNewExerciseClicked() }) {
            Text(
                modifier = Modifier.padding(dimens.space_8),
                text = "Add exercise",
                fontSize = dimens.font_16
            )
        }
    }
}