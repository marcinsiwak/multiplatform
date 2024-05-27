package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi
import pl.msiwak.multiplatform.commonResources.theme.dimens

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AdmobBanner(modifier: Modifier) {
//    UIKitViewController(
//        factory = {
//            BannerViewController()
//        },
//        modifier = Modifier.fillMaxWidth().height(MaterialTheme.dimens.ad_height)
//    )
}
