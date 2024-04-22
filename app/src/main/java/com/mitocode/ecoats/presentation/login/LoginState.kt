package com.mitocode.ecoats.presentation.login

import com.mitocode.ecoats.domain.model.User

data class LoginState (

    val isLoading : Boolean = false,
    val error : String? = null,
    val successfull : User? = null

)