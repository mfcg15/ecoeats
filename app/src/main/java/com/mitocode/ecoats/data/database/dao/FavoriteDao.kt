package com.mitocode.ecoats.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitocode.ecoats.data.database.model.FavoriteEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)

    @Query("select favorite from favorite_table where idUser=:idUser and idDish=:idDish")
    suspend fun favorite(idUser:Int,idDish:Int):Boolean

    @Query("select count(idDish) from favorite_table where idUser=:idUser")
    suspend fun isUser(idUser:Int):Int

    @Query("update favorite_table set favorite=:favorite where idUser=:idUser and idDish=:idDish")
    suspend fun updateFavorite(idUser:Int,idDish:Int,favorite:Boolean)

    @Query("select count(idDish) from favorite_table where idUser=:idUser and favorite=true")
    suspend fun isEmpty(idUser:Int):Int
}