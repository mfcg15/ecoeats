package com.mitocode.ecoats.data.repository

import android.content.SharedPreferences
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.domain.model.Dish
import com.mitocode.ecoats.domain.model.ToDishList
import com.mitocode.ecoats.domain.repository.DishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class DishRepositoryImp @Inject constructor(val sharedPreferences: SharedPreferences) : DishRepository {
    override suspend fun getDishes(): Flow<Result<List<Dish>>> = flow {

        try
        {
            emit(Result.Loading())

            val token = sharedPreferences.getString("KEY_TOKEN","")
            val response = Api.build().getDishes("Bearer $token")

            if(response.isSuccessful)
            {

                val dishes = response.body()?.data?.ToDishList()
                emit(Result.Success(data = dishes))
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
}