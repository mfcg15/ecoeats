package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.domain.model.Dish
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun isEmptyFavorite(idUser:Int) : Flow<Result<Boolean>>

    suspend fun getFavorites() : Flow<Result<List<Dish>>>
}