package com.mitocode.ecoats.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "user_table")
data class UserEntity (

    @PrimaryKey(autoGenerate = false)
    @Nonnull
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "email")
    val email:String
)