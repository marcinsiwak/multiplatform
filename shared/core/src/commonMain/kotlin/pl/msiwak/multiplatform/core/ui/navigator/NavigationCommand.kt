package pl.msiwak.multiplatform.core.ui.navigator

interface NavigationCommand {
    val route: String
    val destination: String
    val isInclusive: Boolean
}