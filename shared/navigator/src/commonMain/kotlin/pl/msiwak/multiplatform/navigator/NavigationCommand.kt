package pl.msiwak.multiplatform.navigator

interface NavigationCommand {
    val route: String
    val destination: String
    val isInclusive: Boolean
}
