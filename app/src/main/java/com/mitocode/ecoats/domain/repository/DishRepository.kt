package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.core.Result
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun getDishesRemote(idUser:Int) : Flow<Result<List<Dish>>>

    suspend fun getDishesLocally(idUser:Int) : Flow<Result<List<Dish>>>

    suspend fun updateFavoriteDish(idUser:Int, idDish : Int, favorite:Boolean)

}