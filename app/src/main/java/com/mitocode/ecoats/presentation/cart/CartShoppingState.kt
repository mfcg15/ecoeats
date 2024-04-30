package com.mitocode.ecoats.presentation.cart

import com.mitocode.ecoats.domain.model.Cart

data class CartShoppingState (
    val isLoading:Boolean=false,
    val error:String?=null,
    val total:Int?=null,
    val cantidad : Int? = null,
    val successfull: List<Cart>? = null
)