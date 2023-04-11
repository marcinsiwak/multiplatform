package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel


@Composable
fun SummaryScreen() {
    val viewModel = koinViewModel<SummaryViewModel>()
    val state = viewModel.viewState.collectAsState()
    val dimens = LocalDim.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(state.value.categories) { category ->
                    CategoryItem(
                        modifier = Modifier
                            .padding(vertical = dimens.space_8)
                            .clickable {
                                viewModel.onExerciseClicked(category.id)
                            },
                        categoryData = category
                    )
                }
                item {
                    Button(
                        modifier = Modifier.padding(dimens.space_16),
                        border = BorderStroke(dimens.space_2, Color.Black),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.onAddExerciseClicked() }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(dimens.space_8),
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = getString(
                                    LocalContext.current,
                                    MR.strings.summary_add_category
                                )
                            )
                        }
                    }
                }
            })
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}