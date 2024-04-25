package com.mitocode.ecoats.domain.repository

import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.core.Result
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun getDishes() : Flow<Result<List<Dish>>>

}