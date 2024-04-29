package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.database.model.UserEntity
import com.mitocode.ecoats.data.networking.model.UserDTO

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

fun UserDTO.ToEntityUser() : UserEntity {
    return UserEntity(
        id = this.id,
        email = this.email
    )
}