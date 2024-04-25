package com.mitocode.ecoats.presentation.dish

import com.mitocode.ecoats.domain.model.Dish

data class DishState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val successfull: List<Dish>? = null
)