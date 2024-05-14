package pl.msiwak.multiplatform.ui.dashboard

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.navigator.destination.BottomNavigationDestination
import pl.msiwak.multiplatform.ui.commonComponent.extension.topBorder

@Composable
fun BottomNavigation(
    navController: NavController,
    items: List<BottomNavigationDestination>
) {
    NavigationBar(
        modifier = Modifier.topBorder(
            MaterialTheme.dimens.bottom_navigation_divider_width,
            MaterialTheme.colorScheme.secondary
        ),
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(painterResource(item.iconId), contentDescription = null)
                },
                label = {
                    Text(
                        text = stringResource(item.labelId),
                        fontSize = MaterialTheme.font.font_8
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary

                ),
                alwaysShowLabel = true,
                selected = currentRoute == item.graphRoute,
                onClick = {
                    navController.navigate(item.graphRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
