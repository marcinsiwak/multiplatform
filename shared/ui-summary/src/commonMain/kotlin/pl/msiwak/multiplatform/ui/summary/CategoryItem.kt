package pl.msiwak.multiplatform.ui.summary

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.util.fastForEach
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.bg_gym
import athletetrack.shared.commonresources.generated.resources.bg_running_field
import org.jetbrains.compose.resources.painterResource
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Suppress("MagicNumber")
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category
) {
    val backgroundId = when (category.exerciseType) {
        ExerciseType.RUNNING -> Res.drawable.bg_running_field
        ExerciseType.GYM -> Res.drawable.bg_gym
    }

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(MaterialTheme.dimens.space_12)
            )
            .border(
                MaterialTheme.dimens.space_2,
                MaterialTheme.colorScheme.secondary,
                RoundedCornerShape(MaterialTheme.dimens.space_8)
            )
            .height(MaterialTheme.dimens.space_164)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxSize()
                .clip(RoundedCornerShape(MaterialTheme.dimens.space_8)),
            painter = painterResource(backgroundId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column {
            Text(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(
                            topStart = MaterialTheme.dimens.space_8,
                            bottomEnd = MaterialTheme.dimens.space_8
                        )
                    )
                    .padding(
                        horizontal = MaterialTheme.dimens.space_12,
                        vertical = MaterialTheme.dimens.space_8
                    ),
                text = category.name,
                fontSize = MaterialTheme.font.font_14,
                color = MaterialTheme.colorScheme.onSecondary
            )

            category.exercises.fastForEach {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = MaterialTheme.dimens.space_8),
                    maxLines = 1,
                    text = it.exerciseTitle,
                    fontSize = MaterialTheme.font.font_12,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@DarkLightPreview
@Composable
fun CategoryItemPreview() {
    AppTheme {
        CategoryItem(category = Category())
    }
}
