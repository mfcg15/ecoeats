package com.mitocode.ecoats.presentation.register

import com.mitocode.ecoats.domain.model.User

data class RegisterState(

    val isLoading: Boolean = false,
    val error: String? = null,
    val successfull: User? = null

)