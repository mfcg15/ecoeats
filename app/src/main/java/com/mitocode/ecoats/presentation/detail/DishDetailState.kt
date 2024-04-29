package com.mitocode.ecoats.presentation.detail

data class DishDetailState (
    val isLoading:Boolean=false,
    val error:String?=null,
    val isBuy: Boolean?=null
)