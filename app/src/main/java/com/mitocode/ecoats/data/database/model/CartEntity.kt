package com.mitocode.ecoats.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "cart_table")
data class CartEntity (

    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "idUser")
    val idUser: Int,

    @ColumnInfo(name = "idDish")
    val idDish: Int,

    @ColumnInfo(name = "dishName")
    val dishName: String,

    @ColumnInfo(name = "dishImagen")
    val dishImagen: String,

    @ColumnInfo(name = "cantidad")
    val cantidad:Int,

    @ColumnInfo(name = "precio")
    val precio:Int,

    @ColumnInfo(name = "total")
    val total:Int,

    @ColumnInfo(name = "isBuy")
    val isBuy:Boolean = false
)