package com.mitocode.ecoats.navigation

sealed class ScreenHome(val route:String) {

    object Dish : ScreenHome(route = "dish_screen")

    object DishFavorite : ScreenHome(route = "dish_favorite_screen")

    object Search : ScreenHome(route = "search_screen")

    object DishDetail : ScreenHome(route = "dish_detail_screen")

}