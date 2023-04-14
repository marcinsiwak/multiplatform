package pl.msiwak.multiplatform.android.ui.addCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.DropDownView
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.ui.addCategory.AddCategoryViewModel

@Composable
fun AddCategoryScreen() {
    val viewModel = koinViewModel<AddCategoryViewModel>()
    val state = viewModel.viewState.collectAsState()
    val dimens = LocalDim.current
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Column {
            InputView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimens.space_8),
                value = state.value.name,
                onValueChange = {
                    viewModel.onCategoryNameChanged(it)
                },
                hintText = getString(context, MR.strings.category_name)
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
                .padding(vertical = dimens.space_16, horizontal = dimens.space_80),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = { viewModel.onSaveCategoryClicked()  }
        ) {
            Text(modifier = Modifier.padding(dimens.space_8), text = "Add category", fontSize = dimens.font_16)
        }
    }
}