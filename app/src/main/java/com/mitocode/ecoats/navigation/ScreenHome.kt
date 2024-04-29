package com.mitocode.ecoats.navigation

sealed class ScreenHome(val route:String) {

    object Dish : ScreenHome(route = "dish_screen")

    object DishFavorite : ScreenHome(route = "dish_favorite_screen")

    object Payments : ScreenHome(route = "payments_screen")

    object CartShopping : ScreenHome(route = "cartshopping_screen")

    object DishDetail : ScreenHome(route = "dish_detail_screen/?dishJson={dishJson}"){
        fun createRoute(dishJson:String) = "dish_detail_screen/?dishJson=$dishJson"
    }

}