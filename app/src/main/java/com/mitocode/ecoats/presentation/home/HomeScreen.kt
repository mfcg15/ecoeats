package com.mitocode.ecoats.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.mitocode.ecoats.navigation.ScreenHome
import com.mitocode.ecoats.navigation.SetupNavGraphHome
import com.mitocode.ecoats.presentation.common.NavigationBarComponent
import com.mitocode.ecoats.presentation.common.TopAppBarComponent

@Composable
fun HomeScreen(idUser : Int) {

    val items = listOf(
        BottomNavigationItem(
            title = "Inicio",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            route = ScreenHome.Dish.route
        ),
        BottomNavigationItem(
            title = "Guardado",
            selectedIcon = Icons.Filled.Bookmark,
            unSelectedIcon = Icons.Outlined.BookmarkBorder,
            route = ScreenHome.DishFavorite.route
        ),
        BottomNavigationItem(
            title = "Compras",
            selectedIcon = Icons.Filled.Payments,
            unSelectedIcon = Icons.Outlined.Payments,
            route = ScreenHome.Payments.route
        )
    )

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBarComponent(
                cantidad = 0,
                modifier = Modifier.padding(horizontal = 8.dp),
                onCartShopping = {
                    navController.navigate(ScreenHome.CartShopping.route)
                }
            )
        },
        bottomBar = {
            NavigationBarComponent(
                items = items,
                onNavigationItem = { item ->
                    navController.navigate(item.route){
                        launchSingleTop = true
                    }
                }
            )
        },
    )
    { paddingValues -> SetupNavGraphHome(paddingValues,idUser, navController)}
}

@Preview(name = "HomeScreen", showSystemUi = true)
@Composable
fun HomeScreenPreview() {
   HomeScreen(idUser = 0)
}