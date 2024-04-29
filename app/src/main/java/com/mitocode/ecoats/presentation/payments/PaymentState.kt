package com.mitocode.ecoats.presentation.payments

data class PaymentState (
    val isLoading:Boolean=false,
    val error:String?=null
)