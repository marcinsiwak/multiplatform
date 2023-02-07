package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun BottomNavigation(navController: NavController, items: List<DashboardNavigationDirections>) {
    androidx.compose.material.BottomNavigation(
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
                        fontSize = 8.sp
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.destination,
                onClick = {
                    navController.navigate(item.destination) {

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