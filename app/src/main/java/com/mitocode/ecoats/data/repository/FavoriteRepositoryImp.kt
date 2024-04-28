package com.mitocode.ecoats.data.repository

import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.DishDao
import com.mitocode.ecoats.data.database.dao.FavoriteDao
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.domain.model.ToEntityDishList
import com.mitocode.ecoats.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepositoryImp @Inject constructor(val dishDao: DishDao, val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun isEmptyFavorite(idUser: Int) : Flow<Result<Boolean>> = flow {

        try
        {
            emit(Result.Loading())

            val isEmFavorite = favoriteDao.isEmpty(idUser)

            if(isEmFavorite == 0)
            {
                emit(Result.Success(data = true))
            }
            else
            {
                emit(Result.Success(data = false))
            }
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }

    }

    override suspend fun getFavorites() : Flow<Result<List<Dish>>> = flow {

        try
        {
            emit(Result.Loading())

            val favoriteDish = dishDao.getFavorites()

            if(favoriteDish.isEmpty())
            {
                emit(Result.Error(message = ""))
            }
            else
            {
                emit(Result.Success(data = favoriteDish.ToEntityDishList()))
            }

        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }

    }
}