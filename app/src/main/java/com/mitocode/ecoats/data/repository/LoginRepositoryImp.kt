package com.mitocode.ecoats.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.mitocode.ecoats.core.Result
import com.mitocode.ecoats.data.database.dao.UserDao
import com.mitocode.ecoats.data.database.model.UserEntity
import com.mitocode.ecoats.data.networking.Api
import com.mitocode.ecoats.data.networking.model.LoginRequest
import com.mitocode.ecoats.domain.model.ToEntityDishList
import com.mitocode.ecoats.domain.model.ToEntityUser
import com.mitocode.ecoats.domain.model.User
import com.mitocode.ecoats.domain.model.toDishEntityList
import com.mitocode.ecoats.domain.model.toUser
import com.mitocode.ecoats.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(val sharedPreferences: SharedPreferences, val userDao: UserDao)  : LoginRepository {
    override suspend fun signIn(email: String, password: String) : Flow<Result<User>> = flow {

        try{

            emit(Result.Loading())

            val response = Api.build().logIn(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            if(response.isSuccessful)
            {

                val loginResponse = response.body()

                if(loginResponse?.success == true)
                {
                    sharedPreferences.edit().putString("KEY_TOKEN",loginResponse.data.token).apply()
                    val userEntity = response.body()?.data?.ToEntityUser()
                    if (userEntity != null) {
                        userDao.save(userEntity)
                    }
                    emit(Result.Success(data = loginResponse.data.toUser()))
                }
                else
                {
                    emit(Result.Error(message = loginResponse?.message))
                }
            }
            else
            {
                emit(Result.Error(message = response.message()))
            }
        }
        catch (ex:IOException)
        {
            emit(Result.Error(message = "Compruebe su conexión a internet"))
        }
        catch (ex:Exception)
        {
            emit(Result.Error(message = ex.message))
        }
    }
}