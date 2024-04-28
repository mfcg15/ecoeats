package com.mitocode.ecoats.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitocode.ecoats.data.database.model.DishEntity

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(dishEntity: List<DishEntity>)

    @Query("select * from dish_table")
    suspend fun getAll():List<DishEntity>

    @Query("select count(id) from dish_table")
    suspend fun counter() : Int

    @Query("update dish_table set favorite=:favorite where id=:idDish")
    suspend fun updateDish(favorite:Boolean, idDish : Int)

    @Query("select * from dish_table where favorite=true")
    suspend fun getFavorites() : List<DishEntity>
}