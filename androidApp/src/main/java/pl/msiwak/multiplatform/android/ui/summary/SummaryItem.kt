package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.data.entity.Summary

@Composable
fun SummaryItem(modifier: Modifier = Modifier, summary: Summary) {
    Box(
        modifier = modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(8.dp),
            ).shadow(2.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            .height(132.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.bg_running_field),
            contentScale = ContentScale.Crop,
            contentDescription = "summary item background"
        )
        Text(
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
            text = summary.exerciseType,
            fontSize = 12.sp,
            color = Color.White
        )

        Row(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(Alignment.CenterEnd)
        ) {
            Text(
                modifier = Modifier
                    .height(48.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Bottom),
                text = summary.result,
                fontSize = 32.sp,
                color = Color.LightGray
            )
            Text(
                modifier = Modifier
                    .height(64.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Bottom),
                text = "10s",
                fontSize = 48.sp,
                color = Color.DarkGray
            )
            Text(
                modifier = Modifier
                    .height(80.dp)
                    .padding(horizontal = 8.dp)
                    .align(Alignment.Bottom),
                text = "10s",
                fontSize = 64.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun SummaryItemPreview() {
//    SummaryItem()
}