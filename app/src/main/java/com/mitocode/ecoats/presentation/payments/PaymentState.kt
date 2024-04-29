package com.mitocode.ecoats.presentation.payments

import com.mitocode.ecoats.domain.model.Payment

data class PaymentState (
    val isLoading:Boolean=false,
    val error:String?=null,
    val successfull: List<Payment>? = null
)