package pl.msiwak.multiplatform.navigator.destination

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class BottomNavigationDestination(
    val iconId: DrawableResource,
    val labelId: StringResource,
    val graphRoute: String
)
