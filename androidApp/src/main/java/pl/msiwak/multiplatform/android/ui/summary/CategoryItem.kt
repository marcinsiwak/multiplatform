package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData

@Composable
fun CategoryItem(modifier: Modifier = Modifier, categoryData: CategoryData) {
   val backgroundId = when (categoryData.exerciseType) { //todo maybe share with ios
        ExerciseType.RUNNING -> R.drawable.bg_running_field
        ExerciseType.GYM -> R.drawable.bg_gym
    }
    Box(
        modifier = modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(8.dp),
            )
            .shadow(2.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
            .height(164.dp)
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxSize()
                .alpha(0.5f)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = backgroundId),
            contentScale = ContentScale.Crop,
            contentDescription = "summary item background"
        )
        
        Column {
            Text(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                text = categoryData.name,
                fontSize = 14.sp,
                color = Color.White
            )

            LazyColumn(
                modifier
                    .fillMaxHeight()
            ) {

                items(categoryData.exercises) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 8.dp),
                        maxLines = 1,
                        text = it.name,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color.LightGray,
                    )
                }
            }

        }


//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 16.dp)
//                .align(Alignment.CenterEnd),
//            verticalAlignment = Alignment.Bottom,
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            Text(
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .align(Alignment.Bottom),
//                maxLines = 1,
//                text = thirdResult,
//                fontSize = 28.sp,
//                fontStyle = FontStyle.Italic,
//                color = Color.LightGray,
//            )
//            Text(
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .align(Alignment.Bottom),
//                maxLines = 1,
//                text = secondResult,
//                fontSize = 36.sp,
//                fontStyle = FontStyle.Italic,
//                color = Color.Gray,
//            )
//            Text(
//                modifier = Modifier
//                    .wrapContentHeight()
//                    .align(Alignment.Bottom),
//                maxLines = 1,
//                text = topResult,
//                fontSize = 48.sp,
//                fontStyle = FontStyle.Italic,
//                color = Color.Black,
//            )
//        }
    }
}

@Preview
@Composable
fun SummaryItemPreview() {
//    SummaryItem()
}