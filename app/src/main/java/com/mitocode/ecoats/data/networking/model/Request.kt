package com.mitocode.ecoats.data.networking.model

data class LoginRequest(
    val email:String,
    val password:String
)

data class RegisterRequest(
    val name:String,
    val lastname:String,
    val email:String,
    val password:String
)