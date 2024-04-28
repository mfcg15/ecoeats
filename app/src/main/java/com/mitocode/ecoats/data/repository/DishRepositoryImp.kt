package com.mitocode.ecoats.data.repository

import android.content.SharedPreferences
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.DishDao
import com.mitocode.ecoats.data.database.dao.FavoriteDao
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.domain.model.Favorite
import com.mitocode.ecoats.domain.model.ToDishList
import com.mitocode.ecoats.domain.model.ToEntityDishList
import com.mitocode.ecoats.domain.model.ToEntityFavorite
import com.mitocode.ecoats.domain.model.toDishEntityList
import com.mitocode.ecoats.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DishRepositoryImp @Inject constructor(val sharedPreferences: SharedPreferences, val dishDao: DishDao, val favoriteDao: FavoriteDao) : DishRepository {
    override suspend fun getDishesRemote(idUser:Int): Flow<Result<List<Dish>>> = flow {

        try
        {
            emit(Result.Loading())

            val token = sharedPreferences.getString("KEY_TOKEN","")
            val response = Api.build().getDishes("Bearer $token")

            if(response.isSuccessful)
            {

                val dishesEntity = response.body()?.data?.toDishEntityList()
                val counterDishesRemote = response.body()?.data?.size!!
                val counterDishesLocally = dishDao.counter()
                val isUser = favoriteDao.isUser(idUser)

                if(counterDishesRemote > counterDishesLocally){
                    dishesEntity?.let {
                        dishDao.saveAll(it)
                    }
                }

                if(isUser == 0)
                {
                    dishesEntity?.forEach {
                        val favoriteAux = Favorite(idUser,it.id)
                        val favoriteEntity = favoriteAux.ToEntityFavorite()
                        favoriteDao.saveFavorite(favoriteEntity)
                    }

                    val dishes = response.body()?.data?.ToDishList()
                    emit(Result.Success(data = dishes))
                }
                else
                {
                    dishesEntity?.forEach {
                        val favorite = favoriteDao.favorite(idUser,it.id)
                        dishDao.updateDish(favorite,it.id)
                    }

                    val diEntity = dishDao.getAll()
                    val dishes = diEntity.ToEntityDishList()
                    emit(Result.Success(data = dishes))
                }
            }
            else
            {
                emit(Result.Error(message = response.message()))
            }
        }
        catch (ex: IOException)
        {
            emit(Result.Error(message = "Compruebe su conexión a internet"))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }

    override suspend fun getDishesLocally(idUser:Int): Flow<Result<List<Dish>>> = flow {

        try
        {
            emit(Result.Loading())

            val dishesAux = dishDao.getAll()

            dishesAux.forEach {
                val favorite = favoriteDao.favorite(idUser,it.id)
                dishDao.updateDish(favorite,it.id)
            }

            val dishEntity = dishDao.getAll()

            if(dishEntity.isEmpty())
            {
                emit(Result.Error(message = "No hay platos disponibles"))
            }
            else
            {
                emit(Result.Success(data = dishEntity.ToEntityDishList()))
            }
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }

    override suspend fun updateFavoriteDish(idUser:Int, idDish : Int, favorite:Boolean) {
        favoriteDao.updateFavorite(idUser,idDish,favorite)
    }
}