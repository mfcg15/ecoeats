package com.mitocode.ecoats.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "favorite_table")
data class FavoriteEntity (

    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "idUser")
    val idUser: Int,

    @ColumnInfo(name = "idDish")
    val idDish: Int,

    @ColumnInfo(name = "favorite")
    val favorite:Boolean
)
