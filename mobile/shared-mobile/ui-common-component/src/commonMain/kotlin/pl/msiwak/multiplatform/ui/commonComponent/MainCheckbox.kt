package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.ic_check_empty
import athletetrack.shared_mobile.commonresources.generated.resources.ic_check_full
import org.jetbrains.compose.resources.painterResource
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun MainCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    onTextClicked: (() -> Unit),
    text: AnnotatedString
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onCheckedChange(!checked)
            }
                .size(MaterialTheme.dimens.checkbox_size)
                .padding(end = MaterialTheme.dimens.space_8),
            painter = painterResource(
                resource = if (checked) Res.drawable.ic_check_full else Res.drawable.ic_check_empty
            ),
            contentDescription = null
        )

        Text(
            modifier = Modifier.clickable {
                onTextClicked()
            },
            style = MaterialTheme.typography.labelMedium,
            text = text
        )
    }
}
