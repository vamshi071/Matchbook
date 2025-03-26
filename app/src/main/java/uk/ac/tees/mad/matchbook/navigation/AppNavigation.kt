package uk.ac.tees.mad.matchbook.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.matchbook.ui.screen.booking.BookingScreen
import uk.ac.tees.mad.matchbook.ui.screen.confirmation.ConfirmationScreen
import uk.ac.tees.mad.matchbook.ui.screen.detail.DetailScreen
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
            HomeScreen(homeViewModel, navController)
        }

        composable("${Routes.DETAIL_SCREEN}/{id}") {
            val id = it.arguments?.getString("id")?:""
            DetailScreen(id, navController)
        }

        composable("${Routes.BOOKING_SCREEN}/{id}") {
            val id = it.arguments?.getString("id")?:""
            BookingScreen(id, navController)
        }

        composable("${Routes.CONFIRMATION_SCREEN}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })) {
            val id = it.arguments?.getLong("id")?:2
            ConfirmationScreen(id,navController)
        }
    }
}