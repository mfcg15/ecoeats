package com.mitocode.ecoats.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.presentation.cart.CartShoppingScreen
import com.mitocode.ecoats.presentation.detail.DishDetailScreen
import com.mitocode.ecoats.presentation.dish.DishScreen
import com.mitocode.ecoats.presentation.favorite.FavoriteScreen
import com.mitocode.ecoats.presentation.payments.PaymentScreen

@Composable
fun SetupNavGraphHome(paddingValues: PaddingValues, idUser : Int, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenHome.Dish.route
    ){
        composable(route = ScreenHome.Dish.route){
            DishScreen(paddingValues = paddingValues, idUser = idUser,
                onSelectedItem = {dish ->
                    val dishJson = Gson().toJson(dish)
                    navController.navigate(ScreenHome.DishDetail.createRoute(dishJson))
                }
            )
        }

        composable(route = ScreenHome.DishFavorite.route){
            FavoriteScreen(paddingValues = paddingValues, idUser = idUser)
        }

        composable(route = ScreenHome.Payments.route){
            PaymentScreen(paddingValues = paddingValues, idUser = idUser)
        }

        composable(route = ScreenHome.CartShopping.route){
            CartShoppingScreen(paddingValues = paddingValues, idUser = idUser)
        }

        composable(route = ScreenHome.DishDetail.route){
            val dishJson = it.arguments?.getString("dishJson")
            val dish = Gson().fromJson(dishJson, Dish::class.java)
            DishDetailScreen(paddingValues = paddingValues, idUser = idUser, dish = dish,
                onDish = {
                    navController.popBackStack()
                    navController.navigate(route = ScreenHome.Dish.route)
                })
        }
    }

}