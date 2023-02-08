package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel

val viewModel: SummaryViewModel by KoinJavaComponent.inject(SummaryViewModel::class.java)

@Composable
fun SummaryScreen() {
    val state = viewModel.summaryState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        LazyColumn(content = {
            items(state.value.summaries) {
                SummaryItem(Modifier.padding(vertical = 8.dp), it)

            }
        })
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}