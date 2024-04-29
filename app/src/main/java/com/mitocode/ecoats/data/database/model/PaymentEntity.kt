package com.mitocode.ecoats.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "payment_table")
data class PaymentEntity (

    @PrimaryKey(autoGenerate = false)
    @Nonnull
    @ColumnInfo(name = "id")
    val id:String,

    @ColumnInfo(name = "fecha")
    val fecha: String,

    @ColumnInfo(name = "hora")
    val hora: String,

    @ColumnInfo(name = "total")
    val total:Int,

    @ColumnInfo(name = "idUser")
    val idUser: Int,
)