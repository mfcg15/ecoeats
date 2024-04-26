package com.mitocode.ecoats.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "dish_table")
data class DishEntity(
    @PrimaryKey(autoGenerate = false)
    @Nonnull
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "thumbails")
    val thumbails:String,

    @ColumnInfo(name = "image")
    val image:String,

    @ColumnInfo(name = "carbohydrates")
    val carbohydrates:Double,

    @ColumnInfo(name = "proteins")
    val proteins:Double,

    @ColumnInfo(name = "price")
    val price:Double,

    @ColumnInfo(name = "rating")
    val rating:Double,

    @ColumnInfo(name = "ingredients")
    val ingredients:String,

    @ColumnInfo(name = "flagHeader")
    val flagHeader:Boolean
)