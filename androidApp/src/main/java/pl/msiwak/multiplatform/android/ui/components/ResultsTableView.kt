package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun ResultsTableView(results: List<FormattedResultData>, isNewResultEnabled: Boolean ,onAddNewResultClicked: () -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier
                .bottomBorder(3.dp, Color.LightGray)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(modifier = Modifier.padding(16.dp), text = "Weight", color = Color.White)
            Text(modifier = Modifier.padding(16.dp), text = "Reps", color = Color.White)
            Text(modifier = Modifier.padding(16.dp), text = "Date", color = Color.White)
        }
        LazyColumn {

            if (isNewResultEnabled) {
                item {
                    NewResultView()
                }
            }

            if (results.isEmpty() && !isNewResultEnabled) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .clickable {
                                onAddNewResultClicked()
                            },
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        text = "Add first result"
                    )
                }
            }
            items(results) {
                ResultView(result = it.result, amount = it.amount, date = it.date)
            }


            if (results.isNotEmpty() && !isNewResultEnabled) {
                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.white_transparent),
                            contentColor = Color.Black
                        ),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape,
                        onClick = { onAddNewResultClicked() }
                    ) {
                        Text(text = "Add new result", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}