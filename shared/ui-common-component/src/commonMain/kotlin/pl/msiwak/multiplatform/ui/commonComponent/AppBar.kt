package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.dimens.space_16,
                    horizontal = MaterialTheme.dimens.space_24
                ),
                text = title,
                fontSize = MaterialTheme.font.font_24,
                color = Color.White
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(MaterialTheme.dimens.space_8)
                    .clickable {
                        navController.navigateUp()
                    },
                painter = painterResource(Res.drawable.ic_arrow_back),
                contentDescription = null
            )

        },
    )
}
