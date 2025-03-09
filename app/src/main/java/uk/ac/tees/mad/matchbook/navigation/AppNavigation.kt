package uk.ac.tees.mad.matchbook.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.matchbook.ui.screen.home.HomeScreen
import uk.ac.tees.mad.matchbook.ui.screen.home.HomeViewModel
import uk.ac.tees.mad.matchbook.ui.screen.splash.SplashScreen
import uk.ac.tees.mad.matchbook.utils.Routes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val homeViewModel: HomeViewModel = hiltViewModel()

    NavHost(navController, Routes.SPLASH_SCREEN) {

        composable(Routes.SPLASH_SCREEN) {
            SplashScreen(navController)
        }

        composable(Routes.HOME_SCREEN) {
            HomeScreen(homeViewModel)
        }
    }
}