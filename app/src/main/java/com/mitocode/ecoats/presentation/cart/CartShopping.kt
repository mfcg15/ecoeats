package com.mitocode.ecoats.presentation.cart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CartShoppingScreen(paddingValues: PaddingValues,idUser : Int) {

}

@Preview (name = "CartShoppingScreen", showSystemUi = true)
@Composable
fun CartShoppingScreenPreview() {
    CartShoppingScreen(paddingValues = PaddingValues(), idUser = 0)
}