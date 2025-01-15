package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.ic_arrow_back
import org.jetbrains.compose.resources.painterResource
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.dimens.space_16
                ),
                text = title,
                fontSize = MaterialTheme.font.font_20,
                color = MaterialTheme.colorScheme.onTertiary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .clickable {
                        navController.navigateUp()
                    }
                    .padding(MaterialTheme.dimens.space_16),
                painter = painterResource(Res.drawable.ic_arrow_back),
                tint = MaterialTheme.colorScheme.onTertiary,
                contentDescription = null
            )
        }
    )
}
