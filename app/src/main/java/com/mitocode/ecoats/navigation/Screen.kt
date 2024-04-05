package com.mitocode.ecoats.navigation

sealed class Screen (val route : String){

    object Welcome : Screen(route = "welcome_screen")
    object OnBoarding : Screen(route = "onboarding_screen")
    object Login : Screen(route = "login_screen")

}