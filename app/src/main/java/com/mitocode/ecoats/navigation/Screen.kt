package com.mitocode.ecoats.navigation

sealed class Screen (val route : String){

    object Welcome : Screen(route = "welcome_screen")
    object OnBoarding : Screen(route = "onboarding_screen")
    object Login : Screen(route = "login_screen")
    object Register : Screen(route = "register_screen")
    object Home : Screen(route = "home_screen/?idUser={idUser}")
    {
        fun createRoute(idUser:String) = "home_screen/?idUser=${idUser}"
    }
}