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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.ListItemView
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.utils.OnLifecycleEvent
import pl.msiwak.multiplatform.ui.category.CategoryViewModel

@Composable
fun CategoryScreen(id: Long) {
    val viewModel = koinViewModel<CategoryViewModel> { parametersOf(id) }
    val state = viewModel.viewState.collectAsState()

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> viewModel.onInit()
            else -> Unit
        }
    }

    if (state.value.isRemoveExerciseDialogVisible) {
        PopupDialog(
            title = "Remove result",
            description = "Do you want to remove this result",
            confirmButtonTitle = "Yes",
            dismissButtonTitle = "No",
            onConfirmClicked = {
                viewModel.onResultRemoved()
            },
            onDismissClicked = {
                viewModel.onPopupDismissed()
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        if (state.value.isDialogVisible) {
            AddExerciseDialog(
                inputText = state.value.newExerciseName,
                onExerciseTitleChanged = {
                    viewModel.onAddExerciseNameChanged(it)
                },
                onAddExerciseClicked = {
                    viewModel.onAddExerciseClicked()
                },
                onDialogClosed = {
                    viewModel.onDialogClosed()
                }
            )
        }

        Column {
            Image(
                modifier = Modifier
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
                    .height(264.dp),
                painter = painterResource(id = R.drawable.bg_gym),
                contentScale = ContentScale.Crop,
                contentDescription = "category background"
            )

            LazyColumn {
                itemsIndexed(state.value.exerciseList) { index, item ->
                    ListItemView(
                        name = item.name,
                        onItemClick = { viewModel.onExerciseClicked(item.id) },
                        onLongClick = { viewModel.onResultLongClicked(index) }
                    )
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp, horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = { viewModel.onAddNewExerciseClicked() }
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Add exercise", fontSize = 16.sp)
        }
    }
}