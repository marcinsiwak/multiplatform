package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel

@Composable
fun SummaryScreen() {
    val viewModel = koinViewModel<SummaryViewModel>()
    val state = viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(state.value.categories) { category ->
                    CategoryItem(
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.dimens.space_8)
                            .clickable {
                                viewModel.onCategoryClicked(category.id)
                            },
                        category = category
                    )
                }
                item {
                    Button(
                        modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.onAddCategoryClicked() }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                                painter = painterResource(id = R.drawable.ic_add),
                                tint = MaterialTheme.colorScheme.tertiary,
                                contentDescription = null
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = stringResource(MR.strings.summary_add_category.resourceId),
                                color = MaterialTheme.colorScheme.tertiary
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