package com.mitocode.ecoats.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mitocode.ecoats.presentation.welcome.WelcomeScreen

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Welcome.route)
    {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(onClick = {

            })
        }

    }
}