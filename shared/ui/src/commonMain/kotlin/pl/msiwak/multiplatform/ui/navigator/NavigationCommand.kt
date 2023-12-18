package pl.msiwak.multiplatform.ui.navigator

interface NavigationCommand {
    val route: String
    val destination: String
    val isInclusive: Boolean
}
