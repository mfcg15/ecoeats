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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.mitocode.ecoats.navigation.ScreenHome
import com.mitocode.ecoats.navigation.SetupNavGraphHome
import com.mitocode.ecoats.presentation.cart.CartShoppingViewModel
import com.mitocode.ecoats.presentation.common.NavigationBarComponent
import com.mitocode.ecoats.presentation.common.TopAppBarComponent

private val topAppBarStates = listOf(true,false)
private var estado : Boolean = false
@Composable
fun HomeScreen(
    idUser : Int,
    viewmodel : CartShoppingViewModel = hiltViewModel(),
) {

    val state = viewmodel.state

    LaunchedEffect(key1 = true) {
        viewmodel.getCantidadDishCart(idUser)
    }

    var (isCartEmpity, updateCart) = remember { mutableStateOf(topAppBarStates.first()) }

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

            var cantidad = 0;

            viewmodel.getCantidadDishCart(idUser)

            if (isCartEmpity == topAppBarStates[0])
            {
                if (state.error != null)
                {
                    cantidad = 0;
                }

                if (state.isLoading)
                {
                    cantidad = state.cantidad!!
                }
                estado = true
            }
            if (isCartEmpity == topAppBarStates[1])
            {
                estado = false
                cantidad = state.cantidad!!
            }
            TopAppBarComponent(
                cantidad = cantidad,
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
    { paddingValues -> SetupNavGraphHome(paddingValues,idUser, navController,updateCart, estado)}
}

@Preview(name = "HomeScreen", showSystemUi = true)
@Composable
fun HomeScreenPreview() {
   HomeScreen(idUser = 0)
}