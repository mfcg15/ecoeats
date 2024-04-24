package com.mitocode.ecoats.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraphHome(paddingValues: PaddingValues, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenHome.Dish.route
    ){
        composable(route = ScreenHome.Dish.route){

        }

        composable(route = ScreenHome.DishFavorite.route){

        }

        composable(route = ScreenHome.Search.route){
        }

        composable(route = ScreenHome.DishDetail.route){
        }
    }

}