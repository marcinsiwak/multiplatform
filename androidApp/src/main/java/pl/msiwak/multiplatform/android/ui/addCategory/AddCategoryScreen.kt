package pl.msiwak.multiplatform.android.ui.addCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.DropDownView
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel

@Composable
fun AddCategoryScreen() {
    val viewModel = koinViewModel<AddCategoryViewModel>()
    val state = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        InputView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = state.value.name,
            onValueChange = {
                viewModel.onExerciseName(it)
            },
            hintText = "Exercise"
        )
        DropDownView(
            currentValue = state.value.exerciseType.name,
            items = ExerciseType.values().toList(),
            onItemPicked = {
                viewModel.onTypePicked(it)
            })

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = { viewModel.onSaveCategoryClicked()  }
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Add category", fontSize = 16.sp)
        }
    }
}