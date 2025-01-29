package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.fastForEach
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.ic_correct
import athletetrack.shared_frontend.commonresources.generated.resources.ic_wrong
import athletetrack.shared_frontend.commonresources.generated.resources.password_requirements
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonObject.PasswordRequirement
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun PasswordRequirements(modifier: Modifier = Modifier, requirements: List<PasswordRequirement>) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(vertical = MaterialTheme.dimens.space_4),
            text = stringResource(Res.string.password_requirements),
            style = MaterialTheme.typography.labelLarge
        )
        requirements.fastForEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (it.isCorrect) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimens.requirements_icon_size),
                        painter = painterResource(Res.drawable.ic_correct),
                        tint = Color.Green,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimens.requirements_icon_size),
                        painter = painterResource(Res.drawable.ic_wrong),
                        tint = Color.Red,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.dimens.space_8),
                    text = stringResource(it.type.stringResource),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
