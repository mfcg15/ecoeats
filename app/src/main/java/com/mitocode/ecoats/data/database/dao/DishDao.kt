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

    @Query("select *from dish_table order by id desc")
    suspend fun getAll():List<DishEntity>

    @Query("select count(id) from dish_table")
    suspend fun counter() : Int
}