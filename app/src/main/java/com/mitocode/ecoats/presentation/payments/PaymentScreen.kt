package com.mitocode.ecoats.presentation.payments

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PaymentScreen(paddingValues: PaddingValues, idUser : Int) {
}

@Preview(name = "CartShoppingScreen", showSystemUi = true)
@Composable
fun CartShoppingScreenPreview() {
    PaymentScreen(paddingValues = PaddingValues(), idUser = 0)
}