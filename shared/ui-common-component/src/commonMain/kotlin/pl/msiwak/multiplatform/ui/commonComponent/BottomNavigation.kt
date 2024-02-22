package pl.msiwak.multiplatform.ui.commonComponent

//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.navigation.NavController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import dev.icerock.moko.resources.compose.painterResource
//import pl.msiwak.multiplatform.android.ui.extensions.topBorder
//import pl.msiwak.multiplatform.commonResources.theme.dimens
//import pl.msiwak.multiplatform.commonResources.theme.font
//import pl.msiwak.multiplatform.navigator.DashboardNavigationDirections
//import pl.msiwak.multiplatform.ui.commonComponent.extension.topBorder

//@Composable
//fun BottomNavigation(
//    navController: NavController,
//    items: List<DashboardNavigationDirections>
//) {
//    NavigationBar(
//        modifier = Modifier.topBorder(
//            MaterialTheme.dimens.bottom_navigation_divider_width,
//            MaterialTheme.colorScheme.secondary
//        ),
//        contentColor = MaterialTheme.colorScheme.onPrimary
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//        items.forEach { item ->
//            NavigationBarItem(
//                icon = { Icon(painterResource(item.icon), contentDescription = item.title) },
//                label = {
//                    Text(
//                        text = item.title,
//                        fontSize = MaterialTheme.font.font_8
//                    )
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
//                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    indicatorColor = MaterialTheme.colorScheme.primary,
//                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
//                    unselectedTextColor = MaterialTheme.colorScheme.secondary
//
//                ),
//                alwaysShowLabel = true,
//                selected = currentRoute == item.route,
//                onClick = {
//                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { screen_route ->
//                            popUpTo(screen_route) {
//                                saveState = true
//                            }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//}
