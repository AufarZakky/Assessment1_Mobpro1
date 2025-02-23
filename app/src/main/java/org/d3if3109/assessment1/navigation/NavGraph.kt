// SetupNavGraph.kt
package org.d3if3109.assessment1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.d3if3109.assessment1.ui.screen.AboutScreen
import org.d3if3109.assessment1.ui.screen.DetailScreen
import org.d3if3109.assessment1.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Detail.route) { backStackEntry ->
            val jenis = backStackEntry.arguments?.getString("jenis")
            if (jenis != null) {
                DetailScreen(navController = navController, jenis = jenis)
            }
        }
    }
}
