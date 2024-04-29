package com.mitocode.ecoats.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mitocode.ecoats.data.database.model.CartEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInCart(cartEntity: CartEntity)

    @Query("select count(id) from cart_table where idUser=:idUser and idDish=:idDish")
    suspend fun isInCart (idUser:Int, idDish: Int):Int

    @Query("select * from cart_table where idUser=:idUser")
    suspend fun getCArtUser (idUser:Int):List<CartEntity>

    @Query("delete from cart_table where id=:idDishCart")
    suspend fun deleteDishCart (idDishCart:Int)
}