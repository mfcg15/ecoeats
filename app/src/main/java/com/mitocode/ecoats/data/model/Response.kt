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

data class DishResponse(
    val success: Boolean,
    val message:String,
    val data:List<DishDTO>
)

data class DishDTO(
    val id:Int,
    val name:String,
    val description:String,
    val thumbails:String,
    val image:String,
    val carbohydrates:Double,
    val proteins:Double,
    val rating:Double,
    val price:Double,
    val ingredients:String,
    val flagHeader:Boolean
)