package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.database.model.FavoriteEntity

data class Favorite(
    val idUser: Int,
    val idDish: Int,
    val favorite:Boolean = false
)

fun Favorite.ToEntityFavorite() : FavoriteEntity {
    return FavoriteEntity(
        id = 0,
        idUser = this.idUser,
        idDish = this.idDish,
        favorite = this.favorite
    )
}