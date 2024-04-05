package com.mitocode.ecoats.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mitocode.ecoats.presentation.login.LoginScreen
import com.mitocode.ecoats.presentation.on_boarding.OnBoardingScreen
import com.mitocode.ecoats.presentation.welcome.WelcomeScreen

@Composable
fun SetupNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route)
    {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(onClick = {
                navController.popBackStack()
                navController.navigate(route = Screen.OnBoarding.route)
            })
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen(onClick = {
                navController.popBackStack()
                navController.navigate(route = Screen.Login.route)
            })
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
            )
        }
    }
}