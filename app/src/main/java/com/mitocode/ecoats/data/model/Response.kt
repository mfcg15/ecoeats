package com.mitocode.ecoats.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    val success:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:UserDTO
)

data class RegisterResponse(
    @SerializedName("success")
    val success:Boolean,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data:UserDTO
)

data class UserDTO(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("lastname")
    val lastname:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("token")
    val token:String
)