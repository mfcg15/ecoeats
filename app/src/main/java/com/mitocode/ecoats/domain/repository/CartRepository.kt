package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun isDishCart(idUser:Int, idDish : Int) : Flow<Result<Boolean>>

    suspend fun saveDishCart(idUser:Int, idDish : Int, dishName : String, dishImagen : String,cantidad:Int, precio:Int)

}