package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.model.UserDTO

data class User(
    val id:Int,
    val email:String
)

fun UserDTO.toUser() : User{
    return User(
        id = this.id,
        email = this.email
    )
}
