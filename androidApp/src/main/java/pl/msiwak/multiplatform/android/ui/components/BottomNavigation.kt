package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import pl.msiwak.multiplatform.android.ui.extensions.topBorder
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun BottomNavigation(navController: NavController, items: List<DashboardNavigationDirections>) {
    androidx.compose.material.BottomNavigation(
        modifier = Modifier.topBorder(
            MaterialTheme.dimens.bottom_navigation_divider_width,
            Color.DarkGray
        ),
        elevation = MaterialTheme.dimens.bottom_navigation_elevation,
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = MaterialTheme.font.font_8
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}