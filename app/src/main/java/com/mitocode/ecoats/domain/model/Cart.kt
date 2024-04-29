package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.database.model.CartEntity

data class Cart(
    val id : Int,
    val idUser: Int,
    val idDish: Int,
    val dishName : String,
    val dishImagen : String,
    val cantidad : Int,
    val precio : Int,
    val total : Int,
    val isBuy : Boolean = false
)

fun Cart.ToEntityCart() : CartEntity {
    return CartEntity(
        id = this.id,
        idUser = this.idUser,
        idDish = this.idDish,
        dishName = this.dishName,
        dishImagen = this.dishImagen,
        cantidad = this.cantidad,
        precio = this.precio,
        total = this.total,
        isBuy = this.isBuy
    )
}
