package pl.msiwak.multiplatform.android.ui.forceUpdate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import org.example.library.MR
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel

@Composable
fun ForceUpdateScreen() {
    val viewModel: ForceUpdateViewModel by KoinJavaComponent.inject(ForceUpdateViewModel::class.java)
    val context = LocalContext.current
    val dimens = LocalDim.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column {
            Image(
                painter = painterResource(id = MR.images.bg_force_update.drawableResId),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(dimens.space_16),
                text = getString(context, MR.strings.force_update_title),
                color = Color.White,
                fontSize = dimens.font_24
            )
            Text(
                modifier = Modifier.padding(horizontal = dimens.space_16),
                text = getString(context, MR.strings.force_update_description),
                color = Color.White,
                fontSize = dimens.font_16
            )
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(vertical = dimens.space_16, horizontal = dimens.space_80),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = { viewModel.onUpdateClicked() }) {
            Text(
                modifier = Modifier.padding(dimens.space_8),
                text = "Update",
                fontSize = dimens.font_16
            )
        }
    }
}