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

    @Query("select count(id) from cart_table where idUser=:idUser and idDish=:idDish and isBuy=false")
    suspend fun isInCart (idUser:Int, idDish: Int):Int

    @Query("select * from cart_table where idUser=:idUser and isBuy=false")
    suspend fun getCArtUser (idUser:Int):List<CartEntity>

    @Query("select precio from cart_table where id=:idDishCart")
    suspend fun getPrecio(idDishCart : Int):Int

    @Query("select count(id) from cart_table where idUser=:idUser and isBuy=false")
    suspend fun getCantidaCart (idUser:Int):Int

    @Query("update cart_table set cantidad=:cantidad, total =:total where id=:idDishCart")
    suspend fun updateDishCart(cantidad:Int, total:Int,idDishCart : Int)

    @Query("update cart_table set isBuy=true where id=:idDishCart")
    suspend fun updateDishPayment(idDishCart : Int)

    @Query("delete from cart_table where id=:idDishCart")
    suspend fun deleteDishCart (idDishCart:Int)
}