package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun isDishCart(idUser:Int, idDish : Int) : Flow<Result<Boolean>>

    suspend fun saveDishCart(idUser:Int, idDish : Int, dishName : String, dishImagen : String,cantidad:Int, precio:Int)

    suspend fun getCart(idUser:Int): Flow<Result<List<Cart>>>

    suspend fun getTotalPayment(idUser:Int) : Flow<Result<Int>>

    suspend fun updateDishCart(idDishCart:Int, cantidad : Int)

    suspend fun updateCartPayment(idUser:Int)

    suspend fun DeleteDishCart(idDishCart:Int)
}