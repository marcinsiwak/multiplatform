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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.library.MR
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData

@Composable
fun CategoryItem(modifier: Modifier = Modifier, categoryData: CategoryData) {
    val dimens = LocalDim.current

    val backgroundId = when (categoryData.exerciseType) { //todo maybe share with ios
        ExerciseType.RUNNING -> MR.images.bg_running_field.drawableResId
        ExerciseType.GYM -> MR.images.bg_gym.drawableResId
//        ExerciseType.OTHER -> null
    }
    Box(
        modifier = modifier
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(dimens.space_8),
            )
            .shadow(dimens.space_2)
            .border(dimens.space_2, Color.Black, RoundedCornerShape(dimens.space_8))
            .height(dimens.space_164)
            .fillMaxWidth(),
    ) {
        backgroundId?.let {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxSize()
                    .alpha(0.5f)
                    .clip(RoundedCornerShape(dimens.space_8)),
                painter = painterResource(id = backgroundId),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }

        Column {
            Text(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(topStart = dimens.space_8, bottomEnd = dimens.space_8)
                    )
                    .padding(horizontal = dimens.space_12, vertical = dimens.space_8),
                text = categoryData.name,
                fontSize = dimens.font_14,
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
                            .padding(start = dimens.space_8),
                        maxLines = 1,
                        text = it.name,
                        fontSize = dimens.font_12,
                        fontStyle = FontStyle.Italic,
                        color = Color.LightGray,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryItemPreview() {
    CategoryItem(categoryData = CategoryData())
}