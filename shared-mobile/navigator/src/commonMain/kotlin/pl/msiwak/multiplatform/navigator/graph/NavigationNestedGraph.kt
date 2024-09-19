package pl.msiwak.multiplatform.navigator.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationNestedGraph {

    fun create(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )
}
