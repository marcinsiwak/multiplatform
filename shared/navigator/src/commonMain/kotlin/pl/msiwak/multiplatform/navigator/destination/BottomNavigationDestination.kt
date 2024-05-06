package pl.msiwak.multiplatform.navigator.destination

import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

sealed class BottomNavigationDestination(
    val iconId: ImageResource,
    val labelId: StringResource,
    val graphRoute: String
)
