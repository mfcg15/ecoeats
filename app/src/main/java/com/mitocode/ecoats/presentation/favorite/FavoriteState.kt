package com.mitocode.ecoats.presentation.favorite

import com.mitocode.ecoats.domain.model.Dish

data class FavoriteState (
    val isLoading:Boolean=false,
    val error:String?=null,
    val isEmpty:Boolean?=null,
    val successfull: List<Dish>? = null
)