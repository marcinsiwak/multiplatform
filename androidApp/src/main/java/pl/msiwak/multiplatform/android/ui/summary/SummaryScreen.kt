package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray)) {
        SummaryItem(Modifier.padding(vertical = 8.dp))
        SummaryItem(Modifier.padding(vertical = 8.dp))
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}