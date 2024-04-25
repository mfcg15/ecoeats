package com.mitocode.ecoats.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mitocode.ecoats.presentation.cart.CartShoppingScreen
import com.mitocode.ecoats.presentation.detail.DishDetailScreen
import com.mitocode.ecoats.presentation.dish.DishScreen
import com.mitocode.ecoats.presentation.favorite.FavoriteScreen
import com.mitocode.ecoats.presentation.payments.PaymentScreen

@Composable
fun SetupNavGraphHome(paddingValues: PaddingValues, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenHome.Dish.route
    ){
        composable(route = ScreenHome.Dish.route){
            DishScreen(paddingValues = paddingValues)
        }

        composable(route = ScreenHome.DishFavorite.route){
            FavoriteScreen(paddingValues = paddingValues)
        }

        composable(route = ScreenHome.Payments.route){
            PaymentScreen(paddingValues = paddingValues)
        }

        composable(route = ScreenHome.CartShopping.route){
            CartShoppingScreen(paddingValues = paddingValues)
        }

        composable(route = ScreenHome.DishDetail.route){
        }
    }

}