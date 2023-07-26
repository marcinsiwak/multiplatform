package pl.msiwak.multiplatform.android.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.data.common.PasswordRequirement

@Composable
fun PasswordRequirements(modifier: Modifier = Modifier, requirements: List<PasswordRequirement>) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(vertical = MaterialTheme.dimens.space_4),
            text = "Password requirements:",
            style = MaterialTheme.typography.labelLarge,
        )
        requirements.forEach {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (it.isCorrect) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimens.requirements_icon_size),
                        painter = painterResource(id = R.drawable.ic_correct),
                        tint = Color.Green,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimens.requirements_icon_size),
                        painter = painterResource(id = R.drawable.ic_wrong),
                        tint = Color.Red,
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.dimens.space_8),
                    text = stringResource(id = it.type.stringResource.resourceId),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}
