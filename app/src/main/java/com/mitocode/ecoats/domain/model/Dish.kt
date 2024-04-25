package com.mitocode.ecoats.domain.model

import com.mitocode.ecoats.data.model.DishDTO

data class Dish(
    val id:Int,
    val name:String,
    val description:String,
    val thumbails:String,
    val image:String,
    val carbohydrates:Double,
    val proteins:Double,
    val price:Double,
    val rating:Double,
    val ingredients:String,
    val flagHeader:Boolean,
    val favorite:Boolean
)

fun List<DishDTO>.ToDishList() : List<Dish> = map {
    Dish(
        id = it.id,
        name = it.name,
        description = it.description,
        thumbails = it.thumbails,
        image = it.image,
        carbohydrates = it.carbohydrates,
        proteins = it.proteins,
        price = it.price,
        rating = it.rating,
        ingredients = it.ingredients,
        flagHeader = it.flagHeader,
        favorite = false
    )
}